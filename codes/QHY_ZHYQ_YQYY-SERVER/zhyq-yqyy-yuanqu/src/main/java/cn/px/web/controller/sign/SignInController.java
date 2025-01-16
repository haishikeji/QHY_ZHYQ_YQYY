package cn.px.web.controller.sign;

import cn.px.common.annotation.Log;
import cn.px.common.core.controller.BaseController;
import cn.px.common.core.domain.AjaxResult;
import cn.px.common.core.page.TableDataInfo;
import cn.px.common.enums.BusinessType;
import cn.px.common.utils.poi.ExcelUtil;
import cn.px.system.domain.SignIn;
import cn.px.system.service.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会议室签到Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/sign")
public class SignInController extends BaseController {
    @Autowired
    private ISignInService signInService;

    /**
     * 查询会议室签到列表
     */
    @PreAuthorize("@ss.hasPermi('system:in:list')")
    @GetMapping("/list")
    public TableDataInfo list(SignIn signIn) {
        startPage();
        List<SignIn> list = signInService.selectSignInList(signIn);
        return getDataTable(list);
    }

    /**
     * 导出会议室签到列表
     */
    @PreAuthorize("@ss.hasPermi('system:in:export')")
    @Log(title = "会议室签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SignIn signIn) {
        List<SignIn> list = signInService.selectSignInList(signIn);
        ExcelUtil<SignIn> util = new ExcelUtil<SignIn>(SignIn.class);
        util.exportExcel(response, list, "会议室签到数据");
    }

    /**
     * 获取会议室签到详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:in:query')")
    @GetMapping(value = "/{signInId}")
    public AjaxResult getInfo(@PathVariable("signInId") Long signInId) {
        return success(signInService.selectSignInBySignInId(signInId));
    }

    /**
     * 新增会议室签到
     */
    @PreAuthorize("@ss.hasPermi('system:in:add')")
    @Log(title = "会议室签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SignIn signIn) {
        return toAjax(signInService.insertSignIn(signIn));
    }

    /**
     * 修改会议室签到
     */
    @PreAuthorize("@ss.hasPermi('system:in:edit')")
    @Log(title = "会议室签到", businessType = BusinessType.UPDATE)
    @PostMapping("/put")
    public AjaxResult edit(@RequestBody SignIn signIn) {
        return toAjax(signInService.updateSignIn(signIn));
    }

    /**
     * 删除会议室签到
     */
    @PreAuthorize("@ss.hasPermi('system:in:remove')")
    @Log(title = "会议室签到", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{signInIds}")
    public AjaxResult remove(@PathVariable Long[] signInIds) {
        return toAjax(signInService.deleteSignInBySignInIds(signInIds));
    }
}
