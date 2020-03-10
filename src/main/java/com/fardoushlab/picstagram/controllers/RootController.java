package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.PostDto;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.models.Comment;
import com.fardoushlab.picstagram.models.Like;
import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.request_models.CommentRM;
import com.fardoushlab.picstagram.request_models.PostRM;
import com.fardoushlab.picstagram.request_models.UserRM;
import com.fardoushlab.picstagram.services.PostService;
import com.fardoushlab.picstagram.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PostService postService;


    @GetMapping("/")
    public String getDefaultPage(Model model){

        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getHomePage(Model model, Authentication authentication){

        UserDto userDto = userService.getUserDtoByName(authentication.getName());
        UserRM userRM = new UserRM();
        BeanUtils.copyProperties(userDto, userRM);

        List<PostDto> allPost = postService.getAllPostDtoWithCommentAndLike(userDto.getId());

        List<UserDto> userDtoList = userService.getNonFriendUserList(userDto.getId());


        model.addAttribute("user",userRM);
        model.addAttribute("post_list",allPost);
        model.addAttribute("user_list",userDtoList);
        model.addAttribute("comment",new CommentRM());


        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(name="error",required = false) Boolean error){
        model.addAttribute("error",error);
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model, @RequestParam(name="error",required = false) Boolean error){
        model.addAttribute("user", new UserRM());
        model.addAttribute("error",error);
        return "auth/register";
    }


    @PostMapping("/register")
    public String registerUser(Model model, @RequestParam(name="error",required = false) Boolean error, @ModelAttribute(name = "user") UserRM  user){

        System.out.println("reg User: "+user.toString());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setAvatar("defaultavatar.jpg");

        long userId = userService.addUser(userDto);

        userService.setFriendConnection(userId,userId);

        if(userId <= 0){
            model.addAttribute("error",error);
            model.addAttribute("user", user);
            return "redirect:/register";
        }

        model.addAttribute("message","Your account created successfully..");
        return "redirect:/login";


    }

}
