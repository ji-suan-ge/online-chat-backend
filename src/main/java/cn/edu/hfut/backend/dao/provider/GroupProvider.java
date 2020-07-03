package cn.edu.hfut.backend.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.sql.Timestamp;

public class GroupProvider {
    public String updateGroupById(Integer id, String name, String introduction, String avatar) {
        return new SQL() {{
            UPDATE("`group`");
            if (name != null) {
                SET("name = #{name}");
            }
            if (introduction != null) {
                SET("introduction = #{introduction}");
            }
            if (avatar != null) {
                SET("avatar = #{avatar}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
