package com.template.common.beetl.function;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysRoleService;
import com.template.web.sys.utils.SysUserUtils;

@Component
public class RoleFunctions {
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 用户的角色List形式
	 */
	public List<SysRole> getUserRoleList(){
		return SysUserUtils.getUserRoles();
	}
	
	/**
	 * 用户角色map形式 key:角色id
	 */
	public Map<Long, SysRole> getUserRoleMap(){
		return SysUserUtils.getUserRolesMap();
	}
	
}
