package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.PostDto;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;
import com.fardoushlab.picstagram.request_models.PostRM;
import com.fardoushlab.picstagram.services.PostService;
import com.fardoushlab.picstagram.services.UserService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ServletContext servletContext;

    @GetMapping("/post/write-post")
    public String getWritePostPage(Model model){

        model.addAttribute("userpost", new PostRM());

        return "post/write-post";
    }

    @PostMapping("/post/write-post")
    public String savePost(Model model, @RequestParam(name = "files") List<MultipartFile> multipartFiles, @ModelAttribute(name = "userpost") PostRM postRM, Authentication authentication){

        List<String> postImages = new ArrayList<>();

       if (multipartFiles != null){
           System.out.println("Files not null: "+multipartFiles.size());
       }else {
           System.out.println("Files are null: ");

       }

       File directory = new File("D:/project/picstagram/post_images/");
      // File directory = new File( servletContext.getRealPath("D:/project/picstagram/post_images") );

        System.out.println("dir path: "+directory.getAbsolutePath());

        if (!directory.exists()){
            boolean bool = directory.mkdirs();
            if(bool){
                System.out.println("Directory created successfully");
            }else{
                System.out.println("Sorry couldn't create specified directory");
            }
        }

        for(MultipartFile file: multipartFiles ){
            try {

                String pictureName = System.currentTimeMillis() + ".jpg";

                File outputfile = new File(directory, pictureName);

                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();

                postImages.add("/images/post_images/"+pictureName);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Post: "+postRM.toString());
        UserDto dto = userService.getUserDtoByName(authentication.getName());

        PostDto postDto = new PostDto();
        postDto.setPostText(postRM.getPostText());
        postDto.setPostImages(postImages);
        long postid = postService.createNewPost(postDto, dto);

        if (postid > 0){
            System.out.println("Post Creadted, Id "+postid);
        }else {

            System.out.println("Post NOT Creadted");
        }


        return "redirect:/index";
    }
}
