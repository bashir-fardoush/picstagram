package com.fardoushlab.picstagram.services;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.dtos.UserSuggDto;
import com.fardoushlab.picstagram.models.User;
import com.fardoushlab.picstagram.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private HibernateConfig config;

    public UserService(HibernateConfig config) {
        this.config = config;
    }

    @Transactional
    public long addUser(UserDto userDto){

        var session = config.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        LocalDateTime dateTime = LocalDateTime.now();
        User user = new User();
        BeanUtils.copyProperties(userDto,user);

        user.setCreatedAt(dateTime);
        user.setUpdatedAt(dateTime);

        long userId = 0;
        try{
            userId = (long) session.save(user);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return userId;

        }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var session = config.getSession();
        var tx = session.getTransaction();

        if (!tx.isActive()){
            tx = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);

        userCriteriaQuery.where(cb.equal(root.get("username"),username));

        var query  = session.createQuery(userCriteriaQuery);
        var user = new User();
        try {
            user = query.getSingleResult();
        }catch (HibernateException e){

            e.printStackTrace();
        }finally {
            session.close();
        }

        if (user ==  null){
            throw  new UsernameNotFoundException("No user found with this user name.");
        }

        List<GrantedAuthority> authorities = new java.util.ArrayList<>();

       // User finalUser = user;
        authorities.add((GrantedAuthority) () -> "ROLE_USER");


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    public UserDto getUserDtoByName(String userName){

        var session = config.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);

        userCriteriaQuery.where(cb.equal(root.get("username"), userName));
        var query = session.createQuery(userCriteriaQuery);

        var userList = new ArrayList<User>();
        try {
            userList = (ArrayList<User>) query.getResultList();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        var userDto = new UserDto();
        if (userList.size() > 0){

            BeanUtils.copyProperties(userList.get(0),userDto);
        }else {
            //throw new ResourceNotFoundException("No user found");
        }

        return userDto;
    }


    public void updateUserProfile(UserDto userDto) {


        var session = config.getSession();
        var transection = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);

        userCriteriaQuery.where(cb.equal(root.get("username"),userDto.getUsername()));

        var query  = session.createQuery(userCriteriaQuery);
        var user = new User();
        try {
            user = query.getSingleResult();
        }catch (HibernateException e){

            e.printStackTrace();
        }

        BeanUtils.copyProperties(userDto,user);
        user.setUpdatedAt(LocalDateTime.now());

        try {
            session.update(user);
            transection.commit();
        }catch(HibernateException e) {

            if(transection!= null ) {
                transection.rollback();
            }
            e.printStackTrace();

        }finally {
            session.close();
        }

    }

    public List<UserDto> getAllUserList() {

        var session = config.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);


        var query = session.createQuery(userCriteriaQuery);

        var userDtoList = new ArrayList<UserDto>();
        try {

           query.getResultList().forEach(user -> {
               UserDto userDto = new UserDto();
               BeanUtils.copyProperties(user,userDto);
               userDto.setPassword("");
               userDtoList.add(userDto);
           });
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return userDtoList;
    }

    @Transactional
    public void followFriend(long userId, long friendId) {

        var session = config.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        // get the user
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userfollowingQuery = cb.createQuery(User.class);
        Root<User> root = userfollowingQuery.from(User.class);
        //userCriteriaQuery.select(root.get("following"));
        userfollowingQuery.where(cb.equal(root.get("id"),userId));

        //get friend
        CriteriaQuery<User> userFollowedByQuery = cb.createQuery(User.class);
        Root<User> followedRoot = userFollowedByQuery.from(User.class);
        userFollowedByQuery.where(cb.equal(root.get("id"),friendId));


        var followingQuery = session.createQuery(userfollowingQuery);
        var followedByQuery = session.createQuery(userFollowedByQuery);



        try {

            // retrieve user and add following
            User user = followingQuery.getSingleResult();
            List<Long> followingList = user.getFollowing();
            followingList.add(friendId);
            user.setFollowing(followingList);
            session.save(user);

            // get friend and add followed by
            User friend = followedByQuery.getSingleResult();
            List<Long> followedByList = friend.getFollowedBy();
            followedByList.add(userId);
            friend.setFollowedBy(followedByList);
            session.save(friend);

            transaction.commit();


        }catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }


    }
    /*
    *   return all nof friend user
    *   -1 indicated no limit will be applicable
    */
    public List<UserSuggDto> getNonFriendUserList(long userId){
        return getNonFriendUserList(userId, 1, -1);
    }

    /*
     *   return specific number of non friend user
     */
    public List<UserSuggDto> getNonFriendUserList(long userId, int limit){
        return getNonFriendUserList(userId,1,limit);
    }

    /**
     *  return paged non friend users, -1 indicates no limit
    */
    public List<UserSuggDto> getNonFriendUserList(long userId,int pageId, int limit) {


        int offset =0;
        if(pageId== 1){ }
        else{

            offset= (int) ((pageId -1) * limit);
        }

        var session = config.getSession();
        var transaction = session.getTransaction();

        if (!transaction.isActive()){
            transaction = session.beginTransaction();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);
        userCriteriaQuery.where(cb.equal(root.get("id"),userId));

        var userQuery = session.createQuery(userCriteriaQuery);

        List<UserSuggDto> nonfriendDtos = new ArrayList<>();

        try{

            User user = userQuery.getSingleResult();
            List<Long> following = user.getFollowing();

            userCriteriaQuery.where(root.get("id").in(following).not());
            var nonfriendQuery = session.createQuery(userCriteriaQuery);

            if (limit > 0 ){
                nonfriendQuery.setFirstResult(offset);
                nonfriendQuery.setMaxResults(limit);
            }


            List<User> nonfriendList = nonfriendQuery.getResultList();

            nonfriendList.forEach(tempUser ->{
                UserSuggDto userDto = new UserSuggDto();
                BeanUtils.copyProperties(tempUser,userDto);
                userDto.setFollowing(tempUser.getFollowing().size());
                userDto.setFollowedBy(tempUser.getFollowedBy().size());
                userDto.setJoinDate(Util.getStringDate(LocalDate.from(tempUser.getCreatedAt()),Util.DOB_DATE_FORMAT));


                nonfriendDtos.add(userDto);
            } );


        }catch (HibernateException exceptione) {
            exceptione.printStackTrace();
        }finally {
            session.close();
        }




        return nonfriendDtos;
    }
}
