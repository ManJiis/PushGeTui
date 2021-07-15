package top.b0x0.getui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.getui.domain.MessageReq;
import top.b0x0.getui.service.PushMessageService;
import top.b0x0.getui.util.BfpResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TANG
 * @date 2021-07-14
 * @since jdk1.8
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    PushMessageService pushMessageService;

    @PostMapping("login")
    public BfpResult login(String username, String password, String cid) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("cid = " + cid);

        MessageReq pushMsgReqVo = new MessageReq();
        pushMsgReqVo.setText(password);
        pushMsgReqVo.setTitle(username);
        pushMsgReqVo.setTransmissionType(2);
        return pushMessageService.pushSingleMsg(cid, pushMsgReqVo);
    }

    @PostMapping("saveCid")
    public Object saveCid(String cid) {
        System.out.println("cid = " + cid);

        Map<String, Object> returnMap = new HashMap<>(3);
        returnMap.put("status", 200);
        returnMap.put("data", null);
        return returnMap;
    }

    public void login2(String username, String password, String cid) {
        while (true) {

        }
    }

    public void login3(String username, String password, String cid) {
        for (; ; ) {

        }
    }
}
