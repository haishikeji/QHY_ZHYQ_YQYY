package cn.px.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.px.common.annotation.Excel;
import cn.px.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 会议室对象 conference_room
 *
 * @author 品讯科技
 * @date 2024-08
 */
public class ConferenceRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会议室ID */
    private Long conferenceRoomId;

    /** 会议室名称 */
    @Excel(name = "会议室名称")
    private String conferenceRoomName;

    /** 会议室地址 */
    @Excel(name = "会议室地址")
    private String conferenceRoomAddress;

    /** 会议室图片地址（,号分割） */
    private String conferenceRoomUrl;

    /**
     * 当前状态 空闲/正在使用
     */
    private String type;

    /** 创建部门 */
    @Excel(name = "创建部门")
    private Long createDept;

    /**
     * 会议室预约集合
     */
    private List<ConferenceRoomOrder> conferenceRoomOrderList;

    public List<ConferenceRoomOrder> getConferenceRoomOrderList() {
        return conferenceRoomOrderList;
    }

    public void setConferenceRoomOrderList(List<ConferenceRoomOrder> conferenceRoomOrderList) {
        this.conferenceRoomOrderList = conferenceRoomOrderList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setConferenceRoomId(Long conferenceRoomId)
    {
        this.conferenceRoomId = conferenceRoomId;
    }

    public Long getConferenceRoomId()
    {
        return conferenceRoomId;
    }
    public void setConferenceRoomName(String conferenceRoomName)
    {
        this.conferenceRoomName = conferenceRoomName;
    }

    public String getConferenceRoomName()
    {
        return conferenceRoomName;
    }
    public void setConferenceRoomAddress(String conferenceRoomAddress)
    {
        this.conferenceRoomAddress = conferenceRoomAddress;
    }

    public String getConferenceRoomAddress()
    {
        return conferenceRoomAddress;
    }
    public void setCreateDept(Long createDept)
    {
        this.createDept = createDept;
    }

    public Long getCreateDept()
    {
        return createDept;
    }

    public String getConferenceRoomUrl() {
        return conferenceRoomUrl;
    }

    public void setConferenceRoomUrl(String conferenceRoomUrl) {
        this.conferenceRoomUrl = conferenceRoomUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("conferenceRoomId", getConferenceRoomId())
            .append("conferenceRoomName", getConferenceRoomName())
            .append("conferenceRoomAddress", getConferenceRoomAddress())
            .append("createDept", getCreateDept())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
