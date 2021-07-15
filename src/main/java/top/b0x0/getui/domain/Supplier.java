package top.b0x0.getui.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 供应商
 *
 * @author hujiachen
 * @since 1.0 2018-07-06
 */

@Setter
@Getter
@Accessors(chain = true)
@TableName("t_supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty("档案号")
    @NotBlank(message = "档案号不能为空")
    @TableId
    private String id;

    //@ApiModelProperty("渠道Id")
    private String bankId;

    //@ApiModelProperty("平台供应商号  (银行通过后生成)")
    private String supId;

    @NotBlank(message = "供应商名称不能为空")
    //@ApiModelProperty("供应商名称")
    private String name;

    //@ApiModelProperty("日受理额度")
    private BigDecimal quotaDay;

    //@ApiModelProperty("当前日可用额度")
    private BigDecimal availableQuota;

//@Api   //@ApiModelProperty("审核状态   -1:  首次渠道不通过  1: 等待审核  2: 已经发审  3: 总部驳回  4: 未发行(渠道通过)  5: 已发行  6:未发行(转移发行)  7: 已禁止")、
    //@Apibfp  --  2.0.3.0
    //@ApiModelProperty("审核状态   1: 待系统审核  2: 审核通过待发行  3: 等待受理复核  4: 受理复核未通过  5: 拒绝进件（已释放）  6:拒绝进件（待释放）  7: 发行通过待激活   8:已激活  9：激活失败-需要绑所在区域的银行卡（如：开封市-河南新东方村镇银行）")
    private Integer reviewSt;

    //@ApiModelProperty("供应商渠道映射状态   0: 待映射   1:正常     2:映射失败")
    private Integer mappingSt;

    //@ApiModelProperty("审核次数")
    private Integer reviewNum;

    //@ApiModelProperty("供应商状态  1: 正常 2: 未激活  3: 黄名单  4:停用 9:风控执行永久停用(人为操作)")
    private Integer status;

    //@ApiModelProperty("单笔交易限额")
    private BigDecimal singleLimit;

    //@ApiModelProperty("单日受理卡数")
    private Integer receiveCardNum;

    //@ApiModelProperty("当前可受理卡数")
    private Integer availableCardNum;

    //@ApiModelProperty("是否可以跨区交易  0:否   1:是")
    private Integer isCrossArea;

    //@ApiModelProperty("收单机构--分公司/代理商 ID")
    private String companyId;

    //@ApiModelProperty("分公司名称")
    private String companyName;

    //@ApiModelProperty("进件人员ID")
    private String entryUserId;

    //@ApiModelProperty("进件人员名称")
    private String entryUserName;

    //@ApiModelProperty("发行人员ID")
    private String publishUserId;

    //@ApiModelProperty("服务商ID")
    private String servicerId;

    //@ApiModelProperty("服务商名称")
    private String servicerName;

    //@ApiModelProperty("法人姓名")
    @NotBlank(message = "法人姓名不能为空")
    private String settleName;

    //@ApiModelProperty("法人手机号")
    @NotBlank(message = "法人手机号不能为空")
    private String settlePhone;

    //@ApiModelProperty("结算卡号")
    @NotBlank(message = "结算卡号不能为空")
    private String cardNo;

    //@ApiModelProperty("是否对公账户  0: 否  1:是")
    @NotNull(message = "是否对公账户不能为空")
    private Integer isPublicAccount;

    //@ApiModelProperty("对公账户名称")
    private String publicAccountName;

    //@ApiModelProperty("行别")
    @NotBlank(message = "行别不能为空")
    private String bankName;

    //@ApiModelProperty("总行号")
    private String bankNo;

    //@ApiModelProperty("开户行--支行")
    private String openBank;

    //@ApiModelProperty("开户行联行号")
    private String openBankNo;

    //@ApiModelProperty("拦截步骤")
    private Integer intercepStep;

    //@ApiModelProperty("拦截信息")
    private String intercepMsg;

    //@ApiModelProperty("当前意见")
    private String nowJudgment;

    //@ApiModelProperty("进件时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    //@ApiModelProperty("发审时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime reviewTime;

    //@ApiModelProperty("银行反馈时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime bankReviewTime;

    //@ApiModelProperty("发行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime publishTime;

    //@ApiModelProperty("最后更新时间")
    private LocalDateTime updateTime;

    //@ApiModelProperty("最后更新人")
    private String updateBy;

    //@ApiModelProperty("品类名称 冗余detail表")
    private String categoryName;

    //@ApiModelProperty("交易类型 0:金花 1:银花")
    private Integer tradeTp;

    //@ApiModelProperty("触发黄名单次数")
    private Integer yellowNumber;

    //@ApiModelProperty("触发黑名单次数")
    private Integer blackNumber;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@ApiModelProperty("合同到期时间")
	private Date contractEndDate;

	//@ApiModelProperty("合同编号")
	private String contractNo;

    //@ApiModelProperty("城市")
    private String area;

    //@ApiModelProperty("地区码 关联 city表")
    private String areaCode;

    //@ApiModelProperty("审核外键id")
    private Long opinionId;

    @TableField(exist = false)
    private String posId;

    @TableField(exist = false)
    private Integer bankReviewSt;

    //@ApiModelProperty("进件人手机号")
    @TableField(exist = false)
    private String phone;

    //@ApiModelProperty("进件人姓名")
    @TableField(exist = false)
    private String userName;

    //@ApiModelProperty("总交易额")
    @TableField(exist = false)
    private BigDecimal totalTradeAmt;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty("完成时间（激活时间）")
    private LocalDateTime finishTime;

    //@ApiModelProperty("映射成功渠道Id")
    private String bankOkId;

    //@ApiModelProperty("映射失败渠道Id")
    private String bankFailId;

    //@ApiModelProperty("映射成功渠道")
    private String bankOk;

    //@ApiModelProperty("映射失败渠道")
    private String bankFail;

    //@ApiModelProperty("扣/结银行名称")
    private String newBankName;

    //@ApiModelProperty("扣/结银行卡号")
    private String newCardNo;

    //@ApiModelProperty("扣/结银行开户行")
    private String newOpenBank;

}