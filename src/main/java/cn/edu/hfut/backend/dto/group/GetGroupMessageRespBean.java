package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupMessageRespBean {
    private List<GroupMessage> groupMessageList;

    @Data
    public static class GroupMessage {
        private Integer groupId;
        private List<Message> messageList;
    }

}
