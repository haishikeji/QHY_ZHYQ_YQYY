package cn.px.system.service;

import java.util.List;
import cn.px.system.domain.DutySchedule;

/**
 * 上班时间配置Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IDutyScheduleService
{
    /**
     * 查询上班时间配置
     *
     * @param dutyId 上班时间配置主键
     * @return 上班时间配置
     */
    public DutySchedule selectDutyScheduleByDutyId(Long dutyId);

    /**
     * 查询上班时间配置列表
     *
     * @param dutySchedule 上班时间配置
     * @return 上班时间配置集合
     */
    public List<DutySchedule> selectDutyScheduleList(DutySchedule dutySchedule);

    /**
     * 新增上班时间配置
     *
     * @param dutySchedule 上班时间配置
     * @return 结果
     */
    public int insertDutySchedule(DutySchedule dutySchedule);

    /**
     * 修改上班时间配置
     *
     * @param dutySchedule 上班时间配置
     * @return 结果
     */
    public int updateDutySchedule(DutySchedule dutySchedule);

    /**
     * 批量删除上班时间配置
     *
     * @param dutyIds 需要删除的上班时间配置主键集合
     * @return 结果
     */
    public int deleteDutyScheduleByDutyIds(Long[] dutyIds);

    /**
     * 删除上班时间配置信息
     *
     * @param dutyId 上班时间配置主键
     * @return 结果
     */
    public int deleteDutyScheduleByDutyId(Long dutyId);
}
