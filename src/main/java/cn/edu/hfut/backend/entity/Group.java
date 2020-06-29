package cn.edu.hfut.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    //id，群号，群名，群介绍，群头像,群状态
    private Integer id;
    private String groupAccount;
    private String name;
    private String introduction;
    private String avatar;
    private Integer state;
}
