package com.fardoushlab.picstagram.services;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import com.fardoushlab.picstagram.dtos.UserDto;
import com.fardoushlab.picstagram.models.User;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


}
