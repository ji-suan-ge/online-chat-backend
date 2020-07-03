package cn.edu.hfut.backend.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupUserListReqBean {

    @NotNull(message = "groupId不能为空")
    private Integer groupId;

}
