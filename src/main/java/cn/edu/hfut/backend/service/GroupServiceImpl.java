package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.GroupMapper;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import cn.edu.hfut.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static cn.edu.hfut.backend.util.RandomUtil.createGroupAccount;

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
        return groupMapper.getGroupInformBy(null, id);
    }

    @Override
    public Group createGroup(String name, String avatar, String introduction, User user) {
        String groupAccount = createGroupAccount();
        Integer userId = user.getId();
        while (groupMapper.getGroupInformBy(groupAccount, null) != null) {
            groupAccount = createGroupAccount();
        }
        groupMapper.createGroup(name, groupAccount, avatar, introduction);

        // 创建者加入群
        Group group = groupMapper.getGroupInformBy(groupAccount, null);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        groupMapper.addGroup(userId, group.getId(), now);
        return group;
    }

    @Override
    public void updateLastReadMessageId(Integer userId, Integer groupId, Integer messageId) {
        groupMapper.updateLastReadMessageId(userId, groupId, messageId);
    }
}
