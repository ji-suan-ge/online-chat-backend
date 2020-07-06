package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.GroupAllUserList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllGroupUserRespBean {
    private List<GroupAllUserList> allGroupUserList;
}
