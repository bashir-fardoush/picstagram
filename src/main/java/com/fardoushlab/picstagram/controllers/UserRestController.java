package com.fardoushlab.picstagram.controllers;

import com.fardoushlab.picstagram.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    private Logger logger = Logger.getLogger(PostController.class);
    @Autowired
    UserService userService;

    @PostMapping("/user/addfriend")
    public ResponseEntity<?> saveFriend(@RequestParam(name = "userId") long userId, @RequestParam(name = "friendId") long friendId){
        logger.info("user:"+userId+" friend:"+friendId);

        userService.setFriendConnection(userId, friendId);


        return new ResponseEntity<>("friend saved", HttpStatus.OK);

    }
}
