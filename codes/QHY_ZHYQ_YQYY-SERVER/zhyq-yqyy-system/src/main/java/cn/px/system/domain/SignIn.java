package cn.px.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.px.common.annotation.Excel;
import cn.px.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会议室签到对象 sign_in
 *
 * @author 品讯科技
 * @date 2024-08
 */
public class SignIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 签到ID */
    private Long signInId;

    /** 会议室预约ID */
    @Excel(name = "会议室预约ID")
    private Long conferenceRoomOrderId;
    /** 会议室预约名称 */
    private String conferenceRoomOrderName;

    /** 会议室ID */
    @Excel(name = "会议室ID")
    private Long conferenceRoomId;
    /** 会议室名称 */
    private String conferenceRoomName;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户头像地址 */
    @Excel(name = "用户头像地址")
    private String avatar;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 创建部门 */
    @Excel(name = "创建部门")
    private Long createDept;

    public String getConferenceRoomOrderName() {
        return conferenceRoomOrderName;
    }

    public void setConferenceRoomOrderName(String conferenceRoomOrderName) {
        this.conferenceRoomOrderName = conferenceRoomOrderName;
    }

    public String getConferenceRoomName() {
        return conferenceRoomName;
    }

    public void setConferenceRoomName(String conferenceRoomName) {
        this.conferenceRoomName = conferenceRoomName;
    }

    public void setSignInId(Long signInId)
    {
        this.signInId = signInId;
    }

    public Long getSignInId()
    {
        return signInId;
    }
    public void setConferenceRoomOrderId(Long conferenceRoomOrderId)
    {
        this.conferenceRoomOrderId = conferenceRoomOrderId;
    }

    public Long getConferenceRoomOrderId()
    {
        return conferenceRoomOrderId;
    }
    public void setConferenceRoomId(Long conferenceRoomId)
    {
        this.conferenceRoomId = conferenceRoomId;
    }

    public Long getConferenceRoomId()
    {
        return conferenceRoomId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setSignTime(Date signTime)
    {
        this.signTime = signTime;
    }

    public Date getSignTime()
    {
        return signTime;
    }
    public void setCreateDept(Long createDept)
    {
        this.createDept = createDept;
    }

    public Long getCreateDept()
    {
        return createDept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("signInId", getSignInId())
            .append("conferenceRoomOrderId", getConferenceRoomOrderId())
            .append("conferenceRoomId", getConferenceRoomId())
            .append("userId", getUserId())
            .append("avatar", getAvatar())
            .append("nickName", getNickName())
            .append("signTime", getSignTime())
            .append("createDept", getCreateDept())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
