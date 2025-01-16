package cn.px.system.service;

import java.util.List;

import cn.px.common.core.domain.AjaxResult;
import cn.px.system.domain.BomanReservat;

/**
 * 预约Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IBomanReservatService
{
    /**
     * 查询预约
     *
     * @param reservatId 预约主键
     * @return 预约
     */
    public BomanReservat selectBomanReservatByReservatId(Long reservatId);

    /**
     * 查询预约列表
     *
     * @param bomanReservat 预约
     * @return 预约集合
     */
    public List<BomanReservat> selectBomanReservatList(BomanReservat bomanReservat);

    /**
     * 新增预约
     *
     * @param bomanReservat 预约
     * @return 结果
     */
    public int insertBomanReservat(BomanReservat bomanReservat);

    /**
     * 修改预约
     *
     * @param bomanReservat 预约
     * @return 结果
     */
    public int updateBomanReservat(BomanReservat bomanReservat);

    /**
     * 批量删除预约
     *
     * @param reservatIds 需要删除的预约主键集合
     * @return 结果
     */
    public int deleteBomanReservatByReservatIds(Long[] reservatIds);

    /**
     * 删除预约信息
     *
     * @param reservatId 预约主键
     * @return 结果
     */
    public int deleteBomanReservatByReservatId(Long reservatId);

    AjaxResult examine(BomanReservat bomanReservat);

    AjaxResult weekAppointment();

    AjaxResult yydc();
}
