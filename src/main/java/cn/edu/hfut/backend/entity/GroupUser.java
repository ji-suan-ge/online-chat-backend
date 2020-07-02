package cn.edu.hfut.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupUser implements Serializable {

    //id, 群id, 人id, 加入时间, 状态
    private Integer id;
    private Integer groupId;
    private Integer userId;
    private Timestamp joinTime;
    private Integer state;
}
