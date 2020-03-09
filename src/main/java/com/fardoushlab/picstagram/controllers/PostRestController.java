package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.dtos.CommentDto;
import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;
import com.fardoushlab.picstagram.request_models.CommentRM;
import com.fardoushlab.picstagram.services.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PostRestController {
    private Logger logger = Logger.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @PostMapping("/post/addcomment")
    public ResponseEntity<?> addNewComment(@RequestParam(name = "postId")long postId, @RequestParam(name = "userId") long userId, @RequestParam(name = "commentText") String commentText){



         Post post = new Post();
        post.setId(postId);

        User user = new User();
        user.setId(userId);


        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);

        commentDto.setWoner(user);
        commentDto.setPost(post);

        long commentId =  postService.addNewComment(commentDto);
        CommentDto savedCommentDto = postService.getCommentById(commentId);

        CommentRM savedCommentRm = new CommentRM();
        BeanUtils.copyProperties(savedCommentDto,savedCommentRm);

        //return savedCommentRm;
        return new ResponseEntity<>(savedCommentRm, HttpStatus.OK);

    }



    @PostMapping("/post/like")
    public ResponseEntity<?> addNewLike(@RequestParam(name = "postId")long postId, @RequestParam(name = "userId") long userId){

        logger.info("userId: "+userId+" postId: "+postId);

        long likeid = 0;
        boolean isExists = postService.isAlreadyLiked(postId,userId);
        if (isExists){
            long val = postService.removeLike(postId,userId);
            logger.info("removed val: "+val);

        }else {
            likeid =   postService.addNewLike(postId,userId);
        }

        /*return "redirect:/index";*/

        return new ResponseEntity<>(likeid, HttpStatus.OK);
    }
}
