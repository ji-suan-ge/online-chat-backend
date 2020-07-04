package cn.edu.hfut.backend.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyGroupReqBean {
    private Integer id;
    private String name;
    private String introduction;
    private String avatar;
}
