package cn.px.system.service;

import java.util.List;

import cn.px.common.core.domain.AjaxResult;
import cn.px.system.domain.OrderFood;

/**
 * 订餐预约Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IOrderFoodService
{
    /**
     * 查询订餐预约
     *
     * @param orderFoodId 订餐预约主键
     * @return 订餐预约
     */
    public OrderFood selectOrderFoodByOrderFoodId(Long orderFoodId);

    /**
     * 查询订餐预约列表
     *
     * @param orderFood 订餐预约
     * @return 订餐预约集合
     */
    public List<OrderFood> selectOrderFoodList(OrderFood orderFood);
    public AjaxResult selectOrderFoodListRiLi(OrderFood orderFood);

    /**
     * 新增订餐预约
     *
     * @param orderFood 订餐预约
     * @return 结果
     */
    public AjaxResult insertOrderFood(OrderFood orderFood);

    /**
     * 修改订餐预约
     *
     * @param orderFood 订餐预约
     * @return 结果
     */
    public AjaxResult updateOrderFood(OrderFood orderFood);

    /**
     * 批量删除订餐预约
     *
     * @param orderFoodIds 需要删除的订餐预约主键集合
     * @return 结果
     */
    public int deleteOrderFoodByOrderFoodIds(Long[] orderFoodIds);

    /**
     * 删除订餐预约信息
     *
     * @param orderFoodId 订餐预约主键
     * @return 结果
     */
    public int deleteOrderFoodByOrderFoodId(Long orderFoodId);
}
