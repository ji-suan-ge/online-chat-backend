package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.dao.provider.GroupProvider;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import cn.edu.hfut.backend.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("SELECT * from `group` where ID IN " +
            "( SELECT groupId  FROM `groupuser` WHERE userId = #{userId})")
    List<Group> getAllGroup(Integer userId);

    @Select("INSERT INTO groupuser(groupId,userId,joinTime) " +
            "VALUES (#{groupId},#{userId},#{joinTime})")
    void addGroup(Integer userId, Integer groupId, Timestamp joinTime);

    @Update("UPDATE groupuser " +
            "SET lastMessageId = #{messageId} " +
            "WHERE groupId = #{groupId} and userId = #{userId}")
    void updateLastReadMessageId(Integer userId, Integer groupId, Integer messageId);

    @Select("select * from user where user.ID in (" +
            "select userId from groupuser where groupuser.groupId = #{groupId})")
    List<GroupUserList> getGroupUserList(Integer groupId);

    @Select("select * from `group`" +
            "where `group`.groupAccount like concat('%',#{groupAccount},'%')")
    List<Group> getGroupByAccount(Integer groupAccount);


    @UpdateProvider(type = GroupProvider.class, method = "updateGroupById")
    void modifyGroup(Integer id, String name, String introduction, String avatar);

//    @Select("select * from `group` where `group`.id = #{id}")
//    Group getGroupInformById(Integer id);

    @SelectProvider(type = GroupProvider.class, method = "getGroupBy")
    Group getGroupInformBy(String groupAccount, Integer id);

    @Insert("INSERT INTO `group`(name,groupAccount,`introduction`,avatar,state) " +
            "VALUES(#{name},#{account},#{introduction},#{avatar},1)")
    void createGroup(String name, String account, String avatar, String introduction);

    @Select("SELECT ID from `group` where ID IN " +
            "( SELECT groupId  FROM `groupuser` WHERE userId = #{userId})")
    List<Integer> getAllGroupId(Integer userId);

    @Select("SELECT groupuser.groupId FROM `groupuser`" +
            "WHERE userId = #{userId}")
    List<Integer> getAllGroupNum(Integer userId);

    @Select("SELECT * FROM `user`" +
            "WHERE ID in (SELECT groupuser.userId From `groupuser` WHERE groupuser.groupId = #{groupId})")
    List<User> getUserList(Integer groupId);

//    @Select("SELECT groupId from groupuser " +
//            "where userId = #{userId} ")
//    List<Integer> getAllGroupId(Integer userId);
}
