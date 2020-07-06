package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllGroupRespBean {
    private List<GetAllGroupRespBean.GroupAndMessageTime> groupUserList;

    @Data
    public static class GroupAndMessageTime {
        private Integer id;
        private String groupAccount;
        private String name;
        private String introduction;
        private String avatar;
        private Integer state;
        private Timestamp lastMessageTime;
        private Integer newMessageNumber;
    }

}
