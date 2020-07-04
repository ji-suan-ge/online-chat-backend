package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupInformByIdRespBean {
    private Group group;
}
