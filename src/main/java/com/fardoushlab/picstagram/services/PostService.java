package com.fardoushlab.picstagram.services;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import com.fardoushlab.picstagram.dtos.CommentDto;
import com.fardoushlab.picstagram.dtos.PostDto;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.models.Comment;
import com.fardoushlab.picstagram.models.Like;
import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {



    private HibernateConfig hibernateConfig;

    public PostService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Transactional
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
    /*  public List<Post> getAllPostWithCommentAndLike() {

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

            for (int i = 0; i<resultList.size(); i++){
               var comments =  resultList.get(i).getComments();
                var likes=  resultList.get(i).getLikes();
            }

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return resultList;
    }
*/
    public List<PostDto> getAllPostDtoWithCommentAndLike(long userId) {

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

        List<PostDto> resultList = new ArrayList<>();

        try {
            query.getResultList().forEach(post -> {

                PostDto dto = new PostDto();
                BeanUtils.copyProperties(post,dto);
                dto.setComments(post.getComments());
                dto.setLikes(post.getLikes());
                dto.setTotalComment(post.getComments().size());
                dto.setTotalLike(post.getLikes().size());
                dto.setIsLiked(false);
                var likes = dto.getLikes();

                for(int i = 0; i<likes.size(); i++){

                    if (likes.get(i).getWoner().getId() == userId){

                        dto.setIsLiked(true);
                        break;
                    }
                }


                resultList.add(dto);


            });

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return resultList;
    }

    @Transactional
    public long addNewComment(CommentDto commentDto) {
        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);

        comment.setCommentTime(LocalDateTime.now());

        long commentId = 0;
        try{
         commentId = (long) session.save(comment);
         transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            if (transaction!= null){
                transaction.rollback();
            }

        }finally {
            session.close();
        }

        return commentId;
    }

    public CommentDto getCommentById(long commentId){
        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Comment> ccQuery = cb.createQuery(Comment.class);
        Root<Comment> root = ccQuery.from(Comment.class);

        ccQuery.where(cb.equal(root.get("id"), commentId));

        var query = session.createQuery(ccQuery);

      Comment comment = new Comment();

        try {
            comment = query.getSingleResult();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment,commentDto);

        return commentDto;


    }

    public long addNewLike(long postId, long userId) {
        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        Like like = new Like();

        Post post = new Post();
        post.setId(postId);
        User user = new User();
        user.setId(userId);

        like.setPost(post);
        like.setWoner(user);

        long likeId = 0;
        try{
            likeId = (long) session.save(like);
         transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }

        return likeId;
    }

    public boolean isAlreadyLiked(long postId, long userId) {

        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Like> likeCQ = cb.createQuery(Like.class);
        Root<Like> root = likeCQ.from(Like.class);

        likeCQ.where(cb.and(cb.equal(root.get("post"), postId),cb.equal(root.get("woner"), userId)));



        var query = session.createQuery(likeCQ);


        var likeList = new ArrayList<Like>();

        try {
            likeList = (ArrayList<Like>) query.getResultList();
            transaction.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

       if (likeList.size() <= 0){
           return false;
       }else {
           return true;
       }

    }

    @Transactional
    public long removeLike(long postId, long userId) {

        var session = hibernateConfig.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<Like> criteriaDelete = cb.createCriteriaDelete(Like.class);
        Root<Like> root = criteriaDelete.from(Like.class);

        Post post = new Post();
        post.setId(postId);
        criteriaDelete.where(cb.and(cb.equal(root.get("post"), postId),cb.equal(root.get("woner"), userId)));


        var query = session.createQuery(criteriaDelete);

        long val = 0;

        try {
            val = query.executeUpdate();
            transaction.commit();

        }catch (HibernateException e){

            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }

        return val;

    }
}
