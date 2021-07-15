package top.b0x0.getui.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息推送请求实体
 *
 * @author TANG
 * @date 2021-07-14
 **/
@Data
public class PushMsgReqVo implements Serializable {

    //@Api"投放城市")
    private String city;
    //@Api"渠道选择")
    private String channelId;
    //@Api"收单机构")
    private String orgId;
    //@Api"投放终端 全部 Android IOS")
    private String terminal;
    //@Api"投放对象 全部 商户 供应商")
    private String target;
    //@Api"针对群体 全部 首次下载 更新版本")
    private String fotGroups;
    //@Api"特殊对应用户")
    private String specialUser;
    //@Api"消息载体")
    private MessageReq messageReq;
}
