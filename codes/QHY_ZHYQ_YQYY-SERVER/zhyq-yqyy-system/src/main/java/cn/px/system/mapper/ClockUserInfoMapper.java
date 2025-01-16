package cn.px.system.mapper;

import java.util.List;
import cn.px.system.domain.ClockUserInfo;

/**
 * 打卡人员信息Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ClockUserInfoMapper
{
    /**
     * 查询打卡人员信息
     *
     * @param userId 打卡人员信息主键
     * @return 打卡人员信息
     */
    public ClockUserInfo selectClockUserInfoByUserId(Long userId);

    /**
     * 查询打卡人员信息列表
     *
     * @param clockUserInfo 打卡人员信息
     * @return 打卡人员信息集合
     */
    public List<ClockUserInfo> selectClockUserInfoList(ClockUserInfo clockUserInfo);

    /**
     * 新增打卡人员信息
     *
     * @param clockUserInfo 打卡人员信息
     * @return 结果
     */
    public int insertClockUserInfo(ClockUserInfo clockUserInfo);

    /**
     * 修改打卡人员信息
     *
     * @param clockUserInfo 打卡人员信息
     * @return 结果
     */
    public int updateClockUserInfo(ClockUserInfo clockUserInfo);

    /**
     * 删除打卡人员信息
     *
     * @param userId 打卡人员信息主键
     * @return 结果
     */
    public int deleteClockUserInfoByUserId(Long userId);

    /**
     * 批量删除打卡人员信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClockUserInfoByUserIds(Long[] userIds);
}
