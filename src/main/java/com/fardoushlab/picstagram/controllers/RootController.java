package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.*;
import com.fardoushlab.picstagram.exceptions.ResourceAlreadyExistException;
import com.fardoushlab.picstagram.request_models.CommentRM;
import com.fardoushlab.picstagram.request_models.UserRM;
import com.fardoushlab.picstagram.services.PostService;
import com.fardoushlab.picstagram.services.UserService;
import org.apache.log4j.Logger;
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

import javax.validation.Valid;
import java.util.List;

@Controller
public class RootController {

    private Logger logger = Logger.getLogger(RootController.class);

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
    public String getHomePage(Model model,
                              @RequestParam(name = "pageId", required = false) Long pageId,
                              @RequestParam(name = "requestType", required = false) String requestType, Authentication authentication){

        if (pageId == null){
            pageId = Long.valueOf(1);
        }else {
            if (requestType.equals("prev")){
                pageId--;
            }else if (requestType.equals("next")){
                pageId++;
            }

            if (pageId < 1){
                pageId  = Long.valueOf(1);
            }
        }

        logger.info("pageId: "+pageId);

        UserDto userDto = userService.getUserDtoByName(authentication.getName());
        UserDtoMinimal userDtoMinimal = new UserDtoMinimal();
        BeanUtils.copyProperties(userDto,userDtoMinimal);

        List<PostDtoMinimal> pagedMinimalposts = postService.getPagedMinimalposts(userDto.getId(), pageId);

        List<UserSuggDto> userDtoList = userService.getNonFriendUserList(userDto.getId(),20);

        model.addAttribute("user",userDtoMinimal);
        model.addAttribute("post_list",pagedMinimalposts);
        model.addAttribute("user_list",userDtoList);
        model.addAttribute("comment",new CommentRM());
        model.addAttribute("pageId",pageId);

        return "index";

    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(name="error",required = false, defaultValue = "false") Boolean error){

        if (error){
            model.addAttribute("errMsg","Username or password not matched !");
        }
        model.addAttribute("error",error);
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model, @RequestParam(name="error",required = false, defaultValue = "false") Boolean error){

        model.addAttribute("user", new UserRM());
        model.addAttribute("error",error);
        return "auth/register";
    }


    @PostMapping("/register")
    public String registerUser(Model model, @RequestParam(name="error",required = false) Boolean error,@Valid @ModelAttribute(name = "user") UserRM  user){

        System.out.println("reg User: "+user.toString());

        if(userService.isUserAlreadyExists(user.getUsername())){

            throw  new ResourceAlreadyExistException("Sorry, this username is not availabe");

        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setAvatar("/images/avatar/defaultavatar.jpg");

        long userId = userService.addUser(userDto);

        userService.followFriend(userId,userId);

        if(userId <= 0){
            model.addAttribute("error",error);
            model.addAttribute("user", user);
            return "redirect:/register";
        }

        model.addAttribute("message","Your account created successfully..");
        return "redirect:/login";


    }

}
