package cn.px.system.mapper;

import java.util.List;
import cn.px.system.domain.EquipmentConfiguration;
import org.apache.ibatis.annotations.Param;

/**
 * 设备配置（控制抓拍一体机）Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface EquipmentConfigurationMapper
{
    /**
     * 查询设备配置（控制抓拍一体机）
     *
     * @param equipmentId 设备配置（控制抓拍一体机）主键
     * @return 设备配置（控制抓拍一体机）
     */
    public EquipmentConfiguration selectEquipmentConfigurationByEquipmentId(Long equipmentId);

    /**
     * 查询设备配置（控制抓拍一体机）列表
     *
     * @param equipmentConfiguration 设备配置（控制抓拍一体机）
     * @return 设备配置（控制抓拍一体机）集合
     */
    public List<EquipmentConfiguration> selectEquipmentConfigurationList(EquipmentConfiguration equipmentConfiguration);

    /**
     * 新增设备配置（控制抓拍一体机）
     *
     * @param equipmentConfiguration 设备配置（控制抓拍一体机）
     * @return 结果
     */
    public int insertEquipmentConfiguration(EquipmentConfiguration equipmentConfiguration);

    /**
     * 修改设备配置（控制抓拍一体机）
     *
     * @param equipmentConfiguration 设备配置（控制抓拍一体机）
     * @return 结果
     */
    public int updateEquipmentConfiguration(EquipmentConfiguration equipmentConfiguration);

    /**
     * 删除设备配置（控制抓拍一体机）
     *
     * @param equipmentId 设备配置（控制抓拍一体机）主键
     * @return 结果
     */
    public int deleteEquipmentConfigurationByEquipmentId(Long equipmentId);

    /**
     * 批量删除设备配置（控制抓拍一体机）
     *
     * @param equipmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquipmentConfigurationByEquipmentIds(Long[] equipmentIds);

    EquipmentConfiguration selectEquipmentConfigurationByEquipmentName(@Param("equipmentName") String equipmentName);
}
