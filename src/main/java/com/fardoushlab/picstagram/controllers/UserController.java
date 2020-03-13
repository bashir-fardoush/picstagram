package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.dtos.UserSuggDto;
import com.fardoushlab.picstagram.dtos.UsersStat;
import com.fardoushlab.picstagram.request_models.CommentRM;
import com.fardoushlab.picstagram.request_models.UserRM;
import com.fardoushlab.picstagram.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.List;

@Controller
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    ServletContext servletContext;

    @GetMapping("/user/profile")
    public String getProfilepage(Model model, @RequestParam(name = "username", required = false)  String username,  Authentication authentication){


        // while username is not null, then showing profile of a post woner,
        // other wise show logged in user profile
        UserDto userDto = new UserDto();
        if (username != null){

            userDto = userService.getUserDtoByName(username);
            model.addAttribute("mode","visitor");
        }else{
            userDto = userService.getUserDtoByName(authentication.getName());
            model.addAttribute("mode","woner");
        }

        UserRM userRM = new UserRM();

        UsersStat usersStat = userService.getUserStats(userDto.getId());

        BeanUtils.copyProperties(userDto,userRM);
        model.addAttribute("user",userRM);
        model.addAttribute("userStat",usersStat);



        return "user/profile";
    }

    @GetMapping("/user/profile-edit")
    public String getProfileEditpage(Model model, Authentication authentication){

        UserDto dto = userService.getUserDtoByName(authentication.getName());
        UserRM userRM = new UserRM();

        BeanUtils.copyProperties(dto,userRM);
        model.addAttribute("user",userRM);

        return "user/profile-edit";
    }


    @PostMapping("/user/profile-edit")
    public String saveEditedprofile(Model model, @ModelAttribute(name = "user") UserRM userRM, @RequestParam(name = "file")MultipartFile multipartFile ){

        UserDto userDto = userService.getUserDtoByName(userRM.getUsername());
        String pictureName = System.currentTimeMillis() + ".jpg";

        if (!multipartFile.getOriginalFilename().isEmpty()) {
            System.out.println("File not Empty");

            try {

                File directory = new File("D:/project/picstagram/avatar/");


                if (!directory.exists()){
                    boolean bool = directory.mkdirs();

                    if(bool){
                        System.out.println("Directory created successfully");
                    }else{
                        System.out.println("Sorry couldn't create specified directory");
                    }
                }

                System.out.println("dir path: "+directory.getAbsolutePath());

                File outputfile = new File(directory, pictureName);

                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                outputStream.write(multipartFile.getBytes());
                outputStream.flush();
                outputStream.close();

           //     userService.updateuserProfilePicture(user.getName(),"/images/profile/"+pictureName);
                userDto.setAvatar("/images/avatar/"+pictureName);


                model.addAttribute("msg", "File uploaded successfully.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                model.addAttribute("msg", "Failed to save file properly.");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("msg", "Failed to save file properly.");
            }
        }
        else {
            System.out.println("File is Empty");
        }

        //Task:  validate information

        userDto.setFullName(userRM.getFullName());
        userDto.setEmail(userRM.getEmail());
        userDto.setPhone(userRM.getPhone());
        userDto.setBio(userRM.getBio());
        userService.updateUserProfile(userDto);

        System.out.println("Received user details: "+userRM.toString());
        return "redirect:/user/profile";

    }

    @GetMapping("/user/friendSuggestion")
    public  String showFriendSuggestion(Model model, @RequestParam(name = "pageId", required = false, defaultValue = "1") int pageId,
                                        @RequestParam(name = "requestType", required = false) String requestType, Authentication authentication){

        if (requestType != null){
            if (requestType.equals("prev")){
                pageId--;
            }else if (requestType.equals("next")){
                pageId++;
            }
        }

        if (pageId < 1){
            pageId  = 1;
        }

        logger.info("pageId: "+pageId);

        UserDto userDto = userService.getUserDtoByName(authentication.getName());
        UserSuggDto userSuggDto = new UserSuggDto();
        BeanUtils.copyProperties(userDto, userSuggDto);


        List<UserSuggDto> userDtoList = userService.getNonFriendUserList(userDto.getId(), pageId,5);

        model.addAttribute("pageId", pageId);
        model.addAttribute("user",userSuggDto);
        model.addAttribute("user_list",userDtoList);


        return "user/show-friend-sugg";
    }
    /*
    *   @PostMapping("/user/uploadFile")
    public String uploadFiles(@RequestParam(name = "file")MultipartFile multipartFile, ModelMap modelMap){


        org.springframework.security.core.userdetails.User authenticateduser  = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.getUserDtoByName(authenticateduser.getUsername());
        String pictureName = "pp"+user.getId()+".jpg";
        // Save file on system
       if (!multipartFile.getOriginalFilename().isEmpty()) {


            try {


                File directory = new File( servletContext.getRealPath("/WEB-INF/resources/images/profile/") );


                if (!directory.exists()){
                    boolean bool = directory.mkdirs();
                    if(bool){
                        System.out.println("Directory created successfully");
                    }else{
                        System.out.println("Sorry couldn't create specified directory");
                    }
                }

                System.out.println("dir path: "+directory.getAbsolutePath());

                File outputfile = new File(directory, pictureName);

                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                outputStream.write(multipartFile.getBytes());
                outputStream.flush();
                outputStream.close();

                userService.updateuserProfilePicture(user.getName(),"/images/profile/"+pictureName);

                System.out.println("file name: "+outputfile.getName());
                modelMap.addAttribute("fileName",outputfile.getName());
                modelMap.addAttribute("photo_uri",outputfile.getAbsolutePath());
                modelMap.addAttribute("msg", "File uploaded successfully.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                modelMap.addAttribute("msg", "Failed to save file properly.");
            } catch (IOException e) {
                e.printStackTrace();
                modelMap.addAttribute("msg", "Failed to save file properly.");
            }

        } else {
            modelMap.addAttribute("msg", "Please select a valid file..");
        }


       // modelMap.addAttribute("file", multipartFile);

        return "redirect:/index";

    }
*/
}
