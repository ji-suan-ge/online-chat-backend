package cn.edu.hfut.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String password;
    private String account;
    private String nickname;
    private Integer gender;
    private Timestamp birthday;
    private String avatar;
    private String email;
    private Integer state;
    private Integer newMessageNumber;
}
