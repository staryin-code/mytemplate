//Powered By if, Since 2014 - 2020

package com.template.web.service;

import com.template.core.base.TreeNode;
import com.template.core.paging.PageHelper;
import com.template.core.paging.PageInfo;
import com.template.web.dao.SysResourceMapper;
import com.template.web.model.SysResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysResourceService")
public class SysResourceService {

	@Resource
	private SysResourceMapper sysResourceMapper;
	
	/**
	 *新增SysResource
	 */
	public int insertSysResource(Map<String,Object> params){
	     return sysResourceMapper.insertSysResource(params);
	}
	
	/**
	 *更新SysResource
	 *@param {"id":""}
	 */
	public int updateSysResource(Map<String,Object> params){
	     return sysResourceMapper.updateSysResource(params);
	}
	
	/**
	 *删除单个SysResource
	 */
	public int deleteSysResource(Long id){
	   return sysResourceMapper.deleteSysResource(id);
	}
	/**
	 *批量删除SysResource
	 */
	public int deleteSysResources(List<Long> idList){
	   return sysResourceMapper.deleteSysResources(idList);
	}
	
	/**
	 *根据id查找一个SysResource
	 */
	 public SysResource findSysResourceById(Long id){
	   return sysResourceMapper.findSysResourceById(id);
	}
	
	/**
	 * 根据条件分页查询SysResource列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysResource> findSysResourcePageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 
        		Integer.parseInt(params.get("pageSize").toString()),isCount);
        List<SysResource> list=sysResourceMapper.findSysResourceListByParams(params);
        return new PageInfo<SysResource>(list);
	}
	
	/**
	 * 根据条件查询SysResource列表（不分页）
	 */
	public List<SysResource> findSysResourceListByParams(Map<String,Object> params) {
	    return sysResourceMapper.findSysResourceListByParams(params);
	}
/*------------------------------------菜单操作----------------------------------------*/
	
	/**
	 * 菜单管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"resourceName":"菜单名字","resourceId":"菜单id"}
	 * @return
	 */
	public PageInfo<SysResource> findMenuPageById(Map<String, Object> params) {
		PageHelper.startPage(
				Integer.parseInt(params.get("pageNum").toString()),
				Integer.parseInt(params.get("pageSize").toString()));
		List<SysResource> list = sysResourceMapper.findMenuPageById(params);
		return new PageInfo<SysResource>(list);
	}
	
	/**
	 * 菜单管理模块树结构用于ztree插件
	 * 
	 * @return
	 */
	public List<TreeNode> getMenuTreeList() {
		List<SysResource> resList = this.findSysResourceListByParams(null);
		List<TreeNode> menuList = new ArrayList<TreeNode>();
		for (int i = 0; i < resList.size(); i++) {
			TreeNode tn = new TreeNode();
			SysResource res = resList.get(i);
			tn.setId(res.getId());
			tn.setPid(res.getPid() == null ? 0L : res.getPid());
			tn.setName(res.getName());
			menuList.add(tn);
		}
		TreeNode top = new TreeNode();
		top.setId(0L);
		top.setName("全部资源");
		top.setOpen(true);
		menuList.add(top);
		return menuList;
	}
	
	/**
	 * 得到菜单树
	* @return
	 */
	public List<TreeNode> getMenuTree(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", 0);
		List<SysResource> list = sysResourceMapper.findSysResourceListByParams(params);
		List<TreeNode> menuList = new ArrayList<TreeNode>();
		for(int i=0;i<list.size();i++){
			SysResource res = list.get(i);
			TreeNode node = new TreeNode(res.getId(),
					res.getPid(), res.getName(),
					res.getUrl(), res.getIcon());
			menuList.add(node);
		}
		return TreeNode.baseTreeNode(menuList);
	}
	
/*------------------------------------End菜单操作----------------------------------------*/
	
}
