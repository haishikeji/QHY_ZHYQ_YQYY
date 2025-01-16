package cn.px.system.service;

import java.util.List;
import cn.px.system.domain.PersonnelManagement;

/**
 * 人员管理Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IPersonnelManagementService
{
    /**
     * 查询人员管理
     *
     * @param personneId 人员管理主键
     * @return 人员管理
     */
    public PersonnelManagement selectPersonnelManagementByPersonneId(Long personneId);

    /**
     * 查询人员管理列表
     *
     * @param personnelManagement 人员管理
     * @return 人员管理集合
     */
    public List<PersonnelManagement> selectPersonnelManagementList(PersonnelManagement personnelManagement);

    /**
     * 新增人员管理
     *
     * @param personnelManagement 人员管理
     * @return 结果
     */
    public int insertPersonnelManagement(PersonnelManagement personnelManagement);

    /**
     * 修改人员管理
     *
     * @param personnelManagement 人员管理
     * @return 结果
     */
    public int updatePersonnelManagement(PersonnelManagement personnelManagement);

    /**
     * 批量删除人员管理
     *
     * @param personneIds 需要删除的人员管理主键集合
     * @return 结果
     */
    public int deletePersonnelManagementByPersonneIds(Long[] personneIds);

    /**
     * 删除人员管理信息
     *
     * @param personneId 人员管理主键
     * @return 结果
     */
    public int deletePersonnelManagementByPersonneId(Long personneId);
}
