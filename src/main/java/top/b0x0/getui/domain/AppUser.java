package top.b0x0.getui.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wwh
 * @since 1.0 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value = "AppUser对象", description = "")
@TableName("app_user")
public class AppUser extends Model<AppUser> implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    //@ApiModelProperty("id")
    private String id;

    //@ApiModelProperty(value = "档案号 关联商户档案号、供应商档案号")
    private String archivesNo;

    //@ApiModelProperty(value = "用户号 关联商户号，供应商号")
    private String userNo;

    //@ApiModelProperty("用户唯一ID标识，主子账户ID")
    private String userId;

    //@ApiModelProperty(value = "用户手机号")
    private String phone;

    //@ApiModelProperty(value = "用户姓名")
    private String userRealName;

    //@ApiModelProperty(value = "是否为主（从）账号 1：主 2：从")
    private Integer status;

    //@ApiModelProperty(value = "用户类型 1：商户 2：供应商")
    private Integer userType;

    //@ApiModelProperty(value = "支付密码")
    private String paymentPassword;

    //@ApiModelProperty(value = "是否开启小额指纹支付 1：是 2：否")
    private Integer fingerprintPay;

    //@ApiModelProperty(value = "是否开小额人脸支付 1：是 2：否")
    private Integer facePay;

    //@ApiModelProperty(value = "是否开启指纹登录1：开启 2：未开启")
    private Integer fingerprintLogin;

    //@ApiModelProperty(value = "是否开启手势登录 1;是 2：否")
    private Integer gestureLogin;

    //@ApiModelProperty(value = "手势密码")
    private String gesturePassword;

    //@ApiModelProperty(value = "是否开启消息推送 1：是 2：否")
    private Integer notification;

    //@ApiModelProperty(value = "是否开启收款语音提醒 1：是 2：否")
    private Integer paymentLanguageReminder;

    //@ApiModelProperty(value = "子账户是否冻结 1：是 2：否")
    private Integer frozen;

    //@ApiModelProperty(value = "身份证号")
    private String idCard;

    //@ApiModelProperty(value = "单日最大总交易额")
    private Double maxAmountTradedDay;

    //@ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    //@ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    //@ApiModelProperty(value = "头像地址")
    private String headImgUrl;

    //@ApiModelProperty(value = "专属客服名称")
    private String servicePersonnel;

    //@ApiModelProperty(value = "专属客服电话")
    private String servicePhone;

    //@ApiModelProperty(value = "售卖概要描述")
    private String category;

    //@ApiModelProperty(value = "手机型号','拼接")
    private String phoneModel;

    //@ApiModelProperty(value = "cid")
    private String cid;

    //@ApiModelProperty("ip地址','拼接")
    private String ip;

    //@ApiModelProperty("app版本号")
    private String version;

    //@ApiModelProperty("进件定位地址名称")
    @TableField(exist = false)
    private String entryAddress;

    //@ApiModelProperty("进件定位地址经度")
    @TableField(exist = false)
    private BigDecimal entryLongitude;

    //@ApiModelProperty("进件定位地址经度")
    @TableField(exist = false)
    private BigDecimal entryLatitude;

/*   @ApiModelProperty("审核状态 0:等待总部审核  1:总部通过(等待渠道审核）  2:总部驳回 3:总部拒绝   5:渠道拒绝(无法重新申请)  6:渠道审核通过（等待发卡)7:转移发行\n" +
            "      8:发卡-未激活 9:发卡已激活（正常）" +
            "12:等待人工复核  13:人工复核有额度通过( 等待渠道审核） 14:人工驳回 15:人工拒绝  16无额度通过" +
            " 17无额度转移发行   18无额度发行成功 19额度激活中")*/
    @TableField(exist = false)
    private Integer stateAudit;

    //@ApiModelProperty("银行供应商号")
    @TableField(exist = false)
    private String bankSupNo;

    //@ApiModelProperty(value = "app类型 1：混合型 2：Android 3：Ios")
    private Integer appType;

    @TableField(exist = false)
    //@ApiModelProperty(value = "供应商/商户 身份证正面图片路径")
    private String urlCardFront;

    @TableField(exist = false)
    //@ApiModelProperty(value = "供应商/商户 所绑定的渠道号")
    private String bankCode;

    @TableField(exist = false)
    //@ApiModelProperty(value = "供应商/商户 店铺名")
    private String shopName;


    @TableField(exist = false)
    //@ApiModelProperty(value = "供应商/经营者")
    private String SupManageName;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
