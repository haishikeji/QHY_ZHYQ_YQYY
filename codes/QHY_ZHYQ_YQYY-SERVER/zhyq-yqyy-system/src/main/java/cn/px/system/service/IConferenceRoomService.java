package cn.px.system.service;

import java.util.List;
import cn.px.system.domain.ConferenceRoom;

/**
 * 会议室Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IConferenceRoomService
{
    /**
     * 查询会议室
     *
     * @param conferenceRoomId 会议室主键
     * @return 会议室
     */
    public ConferenceRoom selectConferenceRoomByConferenceRoomId(Long conferenceRoomId);

    /**
     * 查询会议室列表
     *
     * @param conferenceRoom 会议室
     * @return 会议室集合
     */
    public List<ConferenceRoom> selectConferenceRoomList(ConferenceRoom conferenceRoom);

    /**
     * 新增会议室
     *
     * @param conferenceRoom 会议室
     * @return 结果
     */
    public int insertConferenceRoom(ConferenceRoom conferenceRoom);

    /**
     * 修改会议室
     *
     * @param conferenceRoom 会议室
     * @return 结果
     */
    public int updateConferenceRoom(ConferenceRoom conferenceRoom);

    /**
     * 批量删除会议室
     *
     * @param conferenceRoomIds 需要删除的会议室主键集合
     * @return 结果
     */
    public int deleteConferenceRoomByConferenceRoomIds(Long[] conferenceRoomIds);

    /**
     * 删除会议室信息
     *
     * @param conferenceRoomId 会议室主键
     * @return 结果
     */
    public int deleteConferenceRoomByConferenceRoomId(Long conferenceRoomId);
}
