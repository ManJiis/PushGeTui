package top.b0x0.getui.service;

import top.b0x0.getui.domain.MessageReq;
import top.b0x0.getui.domain.PushMsgReqVo;
import top.b0x0.getui.util.BfpResult;

import java.util.List;

/**
 * @author SCQ
 * @Date 2020/6/9
 */
public interface PushMessageService {
    /**
     * 单推
     *
     * @param cid
     * @param messageReq
     * @return
     */
    BfpResult pushSingleMsg(String cid, MessageReq messageReq);

    /**
     * 条件推送
     *
     * @param
     * @param messageReq
     * @return
     */
    BfpResult pushListMsg(PushMsgReqVo messageReq);

    /**
     * 根据用户标签批量推送
     *
     * @param tags       标签
     * @param messageReq
     * @param appType APP版本
     * @return
     */
    BfpResult pushToApp(List<String> tags, MessageReq messageReq, String appType);

}