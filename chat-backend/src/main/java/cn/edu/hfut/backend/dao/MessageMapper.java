package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.Message;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * from message WHERE (userId = #{userId} and friendId = #{friendId} and type = 1) " +
            "or (userId = #{friendId} and friendId = #{userId} and type = 1)")
    List<Message> selectMessage(Integer userId, Integer friendId);

    @Insert("INSERT INTO " +
            "message(userId,friendId,groupId,type,content,time, state) " +
            "VALUES(#{userId},#{friendId},#{groupId},#{type},#{content},#{time}, #{state})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    void insertMessage(Message message);

    @Select("SELECT * from message WHERE  " +
            "( userId = #{friendId} and friendId = #{userId} and type = 1 and state = 1)")
    List<Message> selectNotPullMessage(Integer userId, Integer friendId);

    @Select("SELECT * from message WHERE  " +
            "( userId = #{friendId} and friendId = #{userId} and type = 1 and state = 2)")
    List<Message> selectIsPullMessage(Integer userId, Integer friendId);

    @Update("UPDATE message SET state = 2 WHERE (userId = #{userId} and friendId = #{friendId} and type = 1 and state = 1)"+
            " or (userId = #{friendId} and friendId = #{userId} and type = 1 and state = 1)")
    void updateMessage(Integer userId, Integer friendId);
}
