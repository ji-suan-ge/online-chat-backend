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
    private Integer id;
    private Integer groupId;
    private Integer userId;
    private Timestamp joinTime;
    private Integer state;
    private Integer lastMessageId;
}
