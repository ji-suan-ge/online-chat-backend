package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.Group;

import java.sql.Timestamp;
import java.util.List;

public interface GroupService {
    List<Group> getAllGroup(Integer userId);

    void addGroup(Integer userId, Integer groupId, Timestamp now);
}
