package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.request_models.UserRM;
import com.fardoushlab.picstagram.services.UserService;
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

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ServletContext servletContext;

    @GetMapping("/user/profile")
    public String getProfilepage(Model model, Authentication authentication){

         UserDto dto = userService.getUserDtoByName(authentication.getName());
        UserRM userRM = new UserRM();

        BeanUtils.copyProperties(dto,userRM);
        model.addAttribute("user",userRM);

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
        String pictureName = "ua"+userDto.getId()+".jpg";

        if (!multipartFile.getOriginalFilename().isEmpty()) {
            System.out.println("File not Empty");

            try {

                File directory = new File( servletContext.getRealPath("/WEB-INF/resources/images/avatar/") );


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
                userDto.setAvatar(pictureName);


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
