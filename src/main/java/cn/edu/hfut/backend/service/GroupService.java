package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dto.group.GetAllGroupRespBean;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import cn.edu.hfut.backend.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface GroupService {
    List<GetAllGroupRespBean.GroupAndMessageTime> getAllGroup(Integer userId);

    void addGroup(Integer userId, Integer groupId, Timestamp now);

    List<User> getGroupUserList(Integer groupId);

    List<Group> getGroupByAccount(Integer groupAccount);

    void modifyGroup(Integer id, String name, String introduction, String avatar);

    Group getGroupInformById(Integer id);

    Group createGroup(String name, String avatar, String introduction, User user);

    void updateLastReadMessageId(Integer groupId, Integer userId, Integer messageId);

    List<Integer> getAllGroupNum(Integer userId);

    List<User> getUserList(Integer groupId);

    Integer getLastMessageId(Integer groupId, Integer userId);
}
