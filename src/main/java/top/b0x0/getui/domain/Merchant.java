package top.b0x0.getui.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: 商户表（工单为wo表，店铺详情是detail表,法人信息corp表，发行卡信息 card表）
 * @Author hujiachen
 * @Date 2018-06-28
 */
@Data
@TableName("t_merchant")
@Accessors(chain = true)
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户档案号 = 商户进件档案号
     */
    @TableId("id")
    //@ApiModelProperty("商户档案号 = 商户进件档案号")
    private String id;
    /**
     * 商户号 (该商户通过银行审核之后生成)
     */
    //@ApiModelProperty("商户号")
    private String merNo;

    //@ApiModelProperty("上游商户档案号")
    private String upperId;

    //@ApiModelProperty("审核状态")
    private Integer stateAudit;

    /**
     * 当前工单id，每修改一次都重新生成工单记录，保存工单信息
     */
    //@ApiModelProperty("当前工单id")
    private String applyId;
    /**
     * 商户名称
     */
    //@ApiModelProperty("商户名称")
    private String merName;
    /**
     * 行业类别号(进件时选择的)
     */
    //@ApiModelProperty("行业类别号(进件时选择的)")
    private String mcc;
    /**
     * 核发银行代码=发审银行(发审渠道value值) : 关联t_bank表
     */
    //@ApiModelProperty("核发银行代码=发审银行(发审渠道value值)")
    private String bankCode;
    /**
     * 银行名称(发审渠道) : 关联t_bank表
     */
    //@ApiModelProperty("银行名称(发审渠道) ")
    private String bankName;
    /**
     * 法人联系电话
     */
    //@ApiModelProperty("法人联系电话")
    private String corpPhone;
    /**
     * 法人姓名
     */
    //@ApiModelProperty("法人姓名")
    private String corpName;

    /**
     * 0:未发行 1:发行未激活 2:已激活
     */
    //@ApiModelProperty("支付pos机审核状态")
    private Integer posState;
    /**
     * 审核次数
     */
    //@ApiModelProperty("审核次数")
    private Integer repulseFreq;
    /**
     * 地区码 关联 area表（数据录入时自动设置）
     */
    //@ApiModelProperty("地区码 关联 city表（数据录入时自动设置）")
    private String areaCode;
    /**
     * 区域 同收单机构区域（数据录入时自动设置）
     */
    //@ApiModelProperty("区域-城市 同收单机构区域（数据录入时自动设置）")
    private String area;
    /**
     * 收单机构---分公司/代理商 id
     */
    //@ApiModelProperty("收单机构---分公司/代理商 id")
    private String orgId;
    /**
     * 收单机构---分公司/代理商名称
     */
    //@ApiModelProperty("收单机构")
    private String orgName;

    /**
     * 收单机构人员(收单员)id
     */
    //@ApiModelProperty("收单机构人员(收单员)id")
    private String orgUserId;
    /**
     * 收单机构人员姓名
     */
    //@ApiModelProperty("收单机构人员姓名")
    private String orgUserName;

    //@ApiModelProperty("经营类别（0：直营 1:加盟）")
    private Integer manageType;

    /**
     * 创建时间=进件时间(之后字段为发行时候录入)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private String updateBy;

    /**
     * 申请通过后的 核发卡号 同 merchant_card的card_no
     */
    private String cardNo;

    //@ApiModelProperty("城市服务商简称")
    private String serviceName;

    //@ApiModelProperty("城市服务商id（如果不是城市服务商进的件，此字段为空）")
    private String serviceId;

    //=============多余列,条件查询方便==============
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty(value = "发审时间", hidden = true)
    @TableField(exist = false)
    private Date acceptTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty(value = "银行反馈时间-核准时间", hidden = true)
    @TableField(exist = false)
    private Date bankAcceptTime;

    //@ApiModelProperty(value = "进件审批", hidden = true)
    @TableField(exist = false)
    private String auditComment;

    //@ApiModelProperty(value = "银行审批结果码", hidden = true)
    @TableField(exist = false)
    private String resultCode;

    //@ApiModelProperty(value = "银行返回结果", hidden = true)
    @TableField(exist = false)
    private String result;

    //@ApiModelProperty(value = "总部审批结果码", hidden = true)
    @TableField(exist = false)
    private String hqResultCode;

    //@ApiModelProperty(value = "总部返回结果", hidden = true)
    @TableField(exist = false)
    private String hqRresult;

    //@ApiModelProperty(value = "获批额度", hidden = true)
    @TableField(exist = false)
    private BigDecimal quota;

    //@ApiModelProperty("聚合码开通 0:开通 1:不开通")
    @TableField(exist = false)
    private Integer openUnionCode;

    //@ApiModelProperty("自动审批触发结果")
    @TableField(exist = false)
    private String msgContent;

    //@ApiModelProperty("自动审批触发步骤")
    @TableField(exist = false)
    private String start;

    //====================商户发行===================
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty(value = "发行时间", hidden = true)
    @TableField(exist = false)
    private Date timeIssue;

    //========================物流信息=================
    //@ApiModelProperty("物流单号")
    @TableField(exist = false)
    private String logisticsNo;

    //@ApiModelProperty("物流状态   0: 在途中  1: 已揽收  2: 疑难  3 已签收")
    @TableField(exist = false)
    private Integer logisticsSt;

    //@ApiModelProperty("物流公司（承运单位）")
    @TableField(exist = false)
    private String logisticsCp;

    //@ApiModelProperty("联系方式")
    @TableField(exist = false)
    private String sysUserPhone;

    //@ApiModelProperty("审核人姓名")
    @TableField(exist = false)
    private String hqApprovalName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty("完成时间（激活时间）")
    private LocalDateTime finishTime;
}
