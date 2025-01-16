package cn.px.web.controller.system;

import cn.px.common.constant.Constants;
import cn.px.common.core.domain.AjaxResult;
import cn.px.common.core.domain.entity.SysMenu;
import cn.px.common.core.domain.entity.SysUser;
import cn.px.common.core.domain.entity.SysUserVo;
import cn.px.common.core.domain.model.LoginBody;
import cn.px.common.core.domain.model.LoginUser;
import cn.px.common.utils.SecurityUtils;
import cn.px.common.utils.StringUtils;
import cn.px.framework.web.service.SysLoginService;
import cn.px.framework.web.service.SysPermissionService;
import cn.px.framework.web.service.TokenService;
import cn.px.system.service.ISysMenuService;
import cn.px.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录验证
 *
 * @author 品讯科技
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 校验token 会议使用
     * @return 结果
     */
    @PostMapping("/checkToken")
    public AjaxResult checkToken(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser user = loginUser.getUser();
            String userName = user.getUserName();
            String nickName = user.getNickName();
            Long userId = user.getUserId();
            String avatar = user.getAvatar();
            Map<String,Object> map = new HashMap<>(2);
            map.put("userName",userName);
            map.put("nickName",nickName);
            map.put("userId",userId);
            map.put("avatar",avatar);
            return AjaxResult.success(map);
        }
        return AjaxResult.error("获取用户信息异常");
    }

    /**
     * 校验token 根据用户ids查询用户信息 会议使用
     * @return 结果
     */
    @PostMapping("/getUserInfoList")
    public AjaxResult getUserInfoList(HttpServletRequest request, @RequestBody SysUserVo sysUserVo)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser sysUser = new SysUser();
            sysUser.setUserIds(sysUserVo.getUserIds());
            List<SysUserVo> userInfoList = userService.getUserInfoList(sysUser);
            return AjaxResult.success(userInfoList);
        }
        return AjaxResult.error("获取用户信息异常");
    }
}
