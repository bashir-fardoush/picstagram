package com.fardoushlab.picstagram.services;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import com.fardoushlab.picstagram.dtos.PostDto;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {



    private HibernateConfig hibernateConfig;

    public PostService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }


    public long createNewPost(PostDto postDto, UserDto userDto) {

        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        LocalDateTime dateTime = LocalDateTime.now();
        User user = new User();
        BeanUtils.copyProperties(userDto,user);

        Post post = new Post();
        post.setPostText(postDto.getPostText());
        post.setPostImages(postDto.getPostImages());
        post.setUser(user);
        post.setPostTime(dateTime);


        long postId = 0;
        try{
            postId = (long) session.save(post);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return postId;

    }

    public Post getPostById(long postid) {

        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Post> postCQ = cb.createQuery(Post.class);
        Root<Post> root = postCQ.from(Post.class);

        postCQ.where(cb.equal(root.get("id"),postid));

        var query  = session.createQuery(postCQ);
        var post = new Post();
        try {
            post = query.getSingleResult();
        }catch (HibernateException e){

            e.printStackTrace();
        }finally {
            session.close();
        }


        return post;

    }

    public List<Post> getAllPost() {

        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Post> postCQ = cb.createQuery(Post.class);
        Root<Post> root = postCQ.from(Post.class);
        postCQ.select(root);
        var query = session.createQuery(postCQ);

        List<Post> resultList = new ArrayList<>();

        try {
             resultList = query.getResultList();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return resultList;
    }
}
