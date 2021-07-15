package top.b0x0.getui.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * "消息推送--消息实体")
 *
 * @author TANG
 * @date 2021-07-14
 **/
@Data
public class MessageReq implements Serializable {

    //@Api"app用户id"
    private String userId;
    //@Api"通知栏标题")
    private String title;
    //@Api"通知栏内容")
    private String text;
    //@Api"透传消息")
    private String transmissionContent;
    //@Api"透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理")
    private Integer transmissionType;
}
