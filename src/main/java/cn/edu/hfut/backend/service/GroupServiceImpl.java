package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.GroupMapper;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Group> getAllGroup(Integer userId) {
        return groupMapper.getAllGroup(userId);
    }

    @Override
    public void addGroup(Integer userId, Integer groupId, Timestamp now) {
        groupMapper.addGroup(userId, groupId, now);
    }

    @Override
    public List<GroupUserList> getGroupUserList(Integer groupId) {
        return groupMapper.getGroupUserList(groupId);
    }

    @Override
    public List<Group> getGroupByAccount(Integer groupAccount) {
        return groupMapper.getGroupByAccount(groupAccount);
    }

    @Override
    public void modifyGroup(Integer id, String name, String introduction, String avatar) {
        groupMapper.modifyGroup(id, name, introduction, avatar);
    }

    @Override
    public Group getGroupInformById(Integer id) {
        return groupMapper.getGroupInformById(id);
    }
}
