package top.b0x0.getui.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.*;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.base.payload.VoIPPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.b0x0.getui.domain.*;
import top.b0x0.getui.mapper.MerchantMapper;
import top.b0x0.getui.service.PushMessageService;
import top.b0x0.getui.mapper.AppUserMapper;
import top.b0x0.getui.util.BfpResult;
import top.b0x0.getui.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息推送
 *
 * @author TANG
 * @date 2021-07-14
 **/
@Component
public class PushMessageServiceImpl implements PushMessageService {
    private static final Logger logger = LogManager.getLogger(PushMessageServiceImpl.class);

    @Autowired(required = false)
    private AppUserMapper appUserMapper;
    @Autowired(required = false)
    private MerchantMapper merchantMapper;


    @Override
    public BfpResult pushSingleMsg(String cid, MessageReq messageReq) {
        if (StringUtils.isBlank(cid) || messageReq == null) {
            return BfpResult.fail("参数不能为空");
        }
        IPushResult result = push(cid, messageReq);
        return pushReturn(result);
    }

    @Override
    public BfpResult pushListMsg(PushMsgReqVo req) {
        QueryWrapper<Merchant> merchantQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        List<Merchant> merchants = merchantMapper.selectList(merchantQueryWrapper);
        List<AppUser> appUsers = appUserMapper.selectList(appUserQueryWrapper);
        List<AppUser> collect = appUsers.stream().filter(appUser -> {
            String userNo = appUser.getUserNo();
            return merchants.stream().map(Merchant::getMerNo).collect(Collectors.toList()).contains(userNo);
        }).collect(Collectors.toList());
        return BfpResult.ok(collect);
    }

    @Override
    public BfpResult pushToApp(List<String> tags, MessageReq messageReq, String appType) {
        if (tags.size() <= 0 || messageReq == null) {
            return BfpResult.fail("参数不能为空");
        }
        IPushResult result = pushMsgToApp(tags, messageReq, appType);
        return pushReturn(result);
    }

    private BfpResult pushReturn(IPushResult result) {
        if (result != null) {
            return BfpResult.ok(result.getResponse().toString());
        } else {
            return BfpResult.fail("个推服务器响应异常");
        }
    }

    /**
     * 消息推送方法抽取
     *
     * @param cid /
     * @param msg /
     * @return /
     */
    private IPushResult push(String cid, MessageReq msg) {
        //@Api设置后，根据别名推送，会返回每个cid的推送结果
        AppUser user = appUserMapper.selectOne(new QueryWrapper<AppUser>().select("version").eq("user_id", msg.getUserId()));
        System.setProperty(com.gexin.rp.sdk.http.Constants.GEXIN_PUSH_SINGLE_ALIAS_DETAIL, "true");
        Target target = new Target();
        IGtPush push;
        if (user == null) {
            user = new AppUser();
            user.setVersion(Constants.APP_TYPE_BASE);
        }
        logger.info("消息推送，APP版本: version>>>{}", user.getVersion());

        push = new IGtPush(Constants.GETUI_HOST_COMMON, Constants.GETUI_APP_KEY_COMMON, Constants.GETUI_MASTER_SECRET_COMMON);
        target.setAppId(Constants.GETUI_APP_ID_COMMON);

        logger.info("1. push---msg: [{}],new SingleMessage():[{}],user.getVersion(): [{}]", JSON.toJSONString(msg), null, user.getVersion());
        SingleMessage message = (SingleMessage) setMessageAndTemplate(msg, new SingleMessage(), user.getVersion());
        target.setClientId(cid);
        IPushResult ret = null;
        logger.info("执行消息推送：cid>>>{}", target.getClientId());
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        logger.info("消息推送返回数据:response>>>{}", ret.getResponse().toString());
        return ret;
    }

    private IPushResult pushList(List<String> clientIds, MessageReq msg, String appType) {
        //@Api配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        //@Api配置返回每个别名及其对应cid的用户状态，可选
        System.setProperty("gexin_pushList_needAliasDetails", "true");
        IGtPush push;
        ListMessage message;
        //@Api配置推送目标
        List<Target> targets = new ArrayList<>();

        push = new IGtPush(Constants.GETUI_HOST_COMMON, Constants.GETUI_APP_KEY_COMMON, Constants.GETUI_MASTER_SECRET_COMMON);
        message = (ListMessage) setMessageAndTemplate(msg, new ListMessage(), appType);
        for (String clientId : clientIds) {
            Target target = new Target();
            target.setAppId(Constants.GETUI_APP_ID_COMMON);
            target.setClientId(clientId);
            targets.add(target);
        }

        //@ApitaskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        return ret;
    }

    private IPushResult pushMsgToApp(List<String> tags, MessageReq msg, String appType) {
        IGtPush push;
        List<String> appIdList = new ArrayList<String>();

        push = new IGtPush(Constants.GETUI_HOST_COMMON, Constants.GETUI_APP_KEY_COMMON, Constants.GETUI_MASTER_SECRET_COMMON);
        appIdList.add(Constants.GETUI_APP_ID_COMMON);

        AppMessage message = (AppMessage) setMessageAndTemplate(msg, new AppMessage(), appType);
        //@Api推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions();
        message.setAppIdList(appIdList);
        //@Api自定义tag
        cdt.addCondition(AppConditions.TAG, tags);
        message.setConditions(cdt);
        IPushResult ret = push.pushMessageToApp(message);
        return ret;
    }

    /**
     * 封装模板
     *
     * @param msg     /
     * @param message /
     * @return /
     */
    private Message setMessageAndTemplate(MessageReq msg, Message message, String appType) {
        logger.info("2. setMessageAndTemplate----msg: [{}],message: [{}],appType: [{}]", JSON.toJSONString(msg), JSON.toJSONString(message), appType);

        NotificationTemplate template = getNotificationTemplate(msg, appType);
        message.setData(template);
        message.setOffline(true);
        //@Api离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //@Api厂商通道下发策略
        message.setStrategyJson("{\"default\":4,\"ios\":4,\"st\":4}");
        template.setAPNInfo(getAPNPayload(msg));
        return message;
    }

    /**
     * 设置模板
     *
     * @param messageReq /
     * @return /
     */
    private static NotificationTemplate getNotificationTemplate(MessageReq messageReq, String appType) {
        logger.info("3. getNotificationTemplate-----messageReq: [{}],appType: [{}]", JSON.toJSONString(messageReq), appType);
        NotificationTemplate template = new NotificationTemplate();
        //@Api设置APPID与APPKEY
        template.setAppId(Constants.GETUI_APP_ID_COMMON);
        template.setAppkey(Constants.GETUI_APP_KEY_COMMON);

        Style0 style = new Style0();
        //@Api设置通知栏标题与内容
        style.setTitle(messageReq.getTitle());
        style.setText(messageReq.getText());
        //@Api配置通知栏网络图标
        style.setLogoUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1680524223,4213057017&fm=11&gp=0.jpg");
        //@Api设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        //style.setChannel("01101010");
        //style.setChannelName("个推消息渠道");
        //设置通知渠道重要性
        style.setChannelLevel(3);
        template.setStyle(style);
        //@Api透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
        template.setTransmissionType(messageReq.getTransmissionType());
        template.setTransmissionContent(messageReq.getTransmissionContent());

        //template.setAPNInfo(getAPNPayload()); //详见【推送模板说明】iOS通知样式设置
        return template;
    }


    /**
     * iOS配置
     *
     * @param msg /
     * @return /
     */
    private static APNPayload getAPNPayload(MessageReq msg) {
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(0);
        //ios 12.0 以上可以使用 Dictionary 类型的 sound
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        payload.addCustomMsg("由客户自定义消息key", "由客户自定义消息value");

        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg.getText()));
        //payload.setAlertMsg(getDictionaryAlertMsg());  //字典模式使用APNPayload.DictionaryAlertMsg

        //设置语音播报类型，int类型，0.不可用 1.播放body 2.播放自定义文本
        payload.setVoicePlayType(2);
        //设置语音播报内容，String类型，非必须参数，用户自定义播放内容，仅在voicePlayMessage=2时生效
        //注：当"定义类型"=2, "定义内容"为空时则忽略不播放
        payload.setVoicePlayMessage("定义内容");

        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.pic)
                .setResUrl("资源文件地址")
                .setOnlyWifi(true));

        return payload;
    }

    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg() {
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody("body1");
        alertMsg.setActionLocKey("显示关闭和查看两个按钮的消息");
        alertMsg.setLocKey("loc-key1");
        alertMsg.addLocArg("loc-ary1");
        alertMsg.setLaunchImage("调用已经在应用程序中绑定的图形文件名");
        //@ApiiOS8.2以上版本支持
        alertMsg.setTitle("通知标题");
        alertMsg.setTitleLocKey("自定义通知标题");
        alertMsg.addTitleLocArg("自定义通知标题组");
        return alertMsg;
    }

    /**
     * 需要使用iOS语音传输，请使用VoIPPayload代替APNPayload
     *
     * @return /
     */
    private static VoIPPayload getVoIPPayload() {
        VoIPPayload payload = new VoIPPayload();
        JSONObject jo = new JSONObject();
        jo.put("key1", "value1");
        payload.setVoIPPayload(jo.toString());
        return payload;
    }
}
