package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.dao.provider.GroupProvider;
import cn.edu.hfut.backend.dao.provider.UserProvider;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

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

    @Select("select user.ID,user.nickname,user.avatar from user where user.ID in (" +
            "select userId from groupuser where groupuser.groupId = #{groupId})")
    List<GroupUserList> getGroupUserList(Integer groupId);

    @Select("select * from `group`" +
            "where `group`.groupAccount like concat('%',#{groupAccount},'%')")
    List<Group> getGroupByAccount(Integer groupAccount);


    @UpdateProvider(type = GroupProvider.class, method = "updateGroupById")
    void modifyGroup(Integer id, String name, String introduction, String avatar);

    @Select("select * from `group` where `group`.id = #{id}")
    Group getGroupInformById(Integer id);


//    @Select("SELECT groupId from groupuser " +
//            "where userId = #{userId} ")
//    List<Integer> getAllGroupId(Integer userId);
}
