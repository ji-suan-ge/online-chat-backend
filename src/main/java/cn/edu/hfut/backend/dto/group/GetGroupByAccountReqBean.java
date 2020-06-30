package cn.edu.hfut.backend.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupByAccountReqBean {
    private Integer groupAccount;

    public Integer getGroupAccount() {
        return groupAccount;
    }

    public void setGroupAccount(Integer groupAccount) {
        this.groupAccount = groupAccount;
    }
}
