package com.ID1212HT22.ID1212.service;

import com.ID1212HT22.ID1212.dao.PlayerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PlayerDao playerdao;
    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    public MyUserDetailsService(@Qualifier("postgres") PlayerDao playerdao){
        this.playerdao = playerdao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String[] cred = new String[2];
        try{
            cred = getCredentials(username);
        }catch(Exception e){
            throw new UsernameNotFoundException("user not found");
        }
        return new User(cred[0], cred[1], new ArrayList<>());
    }

    private String[] getCredentials(String username) throws com.ID1212HT22.ID1212.exceptions.UsernameNotFoundException {
        String[] cred = new String[2];
        try{
            cred[0] = username;
            cred[1] = playerdao.get_password(username);
        }catch(Exception e){
            System.out.println(e);
            logger.error("CREDENTIALS NOT FOUND. USER is not in database: " + username);
            return null;
        }
        return cred;
    }
}
