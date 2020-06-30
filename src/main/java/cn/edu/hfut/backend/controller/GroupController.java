package cn.edu.hfut.backend.controller;

import cn.edu.hfut.backend.constant.code.UserResponseCode;
import cn.edu.hfut.backend.dto.friend.AddFriendReqBean;
import cn.edu.hfut.backend.dto.group.*;
import cn.edu.hfut.backend.entity.Group;
import cn.edu.hfut.backend.entity.GroupUserList;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.GroupService;
import cn.edu.hfut.backend.util.ResultUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping("get")
    public Response getAllGroup(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null){
            return ResultUtil.error(UserResponseCode.NOT_LOGIN,"请先登录！");
        }
        Integer userId = user.getId();
        List<Group> groupList = groupService.getAllGroup(userId);

        GetAllGroupRespBean getAllGroupRespBean = new GetAllGroupRespBean(groupList);
        return ResultUtil.success(getAllGroupRespBean);
    }

    @PostMapping("addGroup")
    public Response addGroup(@RequestBody @Valid AddGroupReqBean addGroupReqBean,
                              HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null){
            return ResultUtil.error(UserResponseCode.NOT_LOGIN,"请先登录！");
        }
        Integer userId = user.getId();
        Integer groupId = addGroupReqBean.getGroupId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        groupService.addGroup(userId, groupId, now);

        return ResultUtil.success();
    }

    @PostMapping("getGroupUserList")
    public Response getGroupUserList(@RequestBody @Valid GetGroupUserListReqBean getGroupUserListReqBean,
                                     HttpSession httpSession) {
        Integer groupId = getGroupUserListReqBean.getGroupId();
        List<GroupUserList> groupUserList = groupService.getGroupUserList(groupId);
        GetGroupUserListRespBean getGroupUserListRespBean = new GetGroupUserListRespBean(groupUserList);
        return ResultUtil.success(getGroupUserListRespBean);
    }

    @PostMapping("getGroupByAccount")
    public Response getGroupByAccount(@RequestBody @Valid GetGroupByAccountReqBean getGroupByAccountReqBean,
                                      HttpSession httpSession) {
        Integer groupAccount = getGroupByAccountReqBean.getGroupAccount();
        List<Group> groupList = groupService.getGroupByAccount(groupAccount);
        GetGroupByAccountRespBean getGroupByAccountRespBean = new GetGroupByAccountRespBean(groupList);
        return ResultUtil.success(getGroupByAccountRespBean);
    }
}
