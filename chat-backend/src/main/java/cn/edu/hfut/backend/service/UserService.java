package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {

    User login(String credential, String password, Integer type);

    void enroll(String account, String password, String email, String nickname,
                String avatar, Timestamp birthday, Integer gender);

    Integer getIdByCredential(String credential, Integer type);

    List<User> getAllFriendById(Integer id);
}
