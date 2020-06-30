package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.GroupUserList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupUserListRespBean {

    private List<GroupUserList> groupUserList;

}
