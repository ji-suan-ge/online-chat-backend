package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("SELECT * from `group` where ID IN " +
            "( SELECT groupId  FROM `groupuser` WHERE userId = #{userId})")
    List<Group> getAllGroup(Integer userId);

//    @Select("SELECT groupId from groupuser " +
//            "where userId = #{userId} ")
//    List<Integer> getAllGroupId(Integer userId);
}
