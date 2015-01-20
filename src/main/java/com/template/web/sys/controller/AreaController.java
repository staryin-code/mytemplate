package com.template.web.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.template.common.utils.JsonUtils;
import com.template.web.sys.model.SysArea;
import com.template.web.sys.service.SysAreaService;



@Controller
@RequestMapping("${adminPath}/area")
public class AreaController {
	
	@Resource
	private SysAreaService sysAreaService;
	
	@RequestMapping
	public String toArea(Model model){
		model.addAttribute("treeList",
				JsonUtils.getInstance().toJson(sysAreaService.findAllArea()));
		return "sys/area/area";
	}
	
	/**
	 * 区域树
	* @return
	 */
	@RequestMapping(value="tree",method = RequestMethod.POST)
	public @ResponseBody List<SysArea> getAreaTreeList(){
		List<SysArea> list = sysAreaService.findAllArea();
		return list;
	}
	
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list",method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysArea> page = sysAreaService.findPageInfo(params);
		model.addAttribute("page", page);
		return "sys/area/area-list";
	}
	
	/**
	 * 添加或更新区域
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute SysArea sysArea) {
		return sysAreaService.saveSysArea(sysArea);
	}

	/**
	 * 删除区域及其子区域
	* @param resourceId 区域id
	* @return
	 */
	@RequestMapping(value="del",method=RequestMethod.POST)
	public @ResponseBody Integer dels(Long id){
		Integer count = 0;
		if(null != id){
			count = sysAreaService.deleteAreaByRootId(id);
		}
		return count;
	}

	/**
	 * 弹窗
	* @param id
	* @param parentId 父类id
	* @param mode 模式(add,edit,detail)
	* @param model
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String showLayer(Long id,Long parentId,@PathVariable("mode") String mode, Model model){
		SysArea area = null, pArea = null;
		if(StringUtils.equalsIgnoreCase(mode, "add")){
			pArea = sysAreaService.selectByPrimaryKey(parentId);
		}else if(StringUtils.equalsIgnoreCase(mode, "edit")){
			area = sysAreaService.selectByPrimaryKey(id);
			pArea = sysAreaService.selectByPrimaryKey(parentId);
		}else if(StringUtils.equalsIgnoreCase(mode, "detail")){
			area = sysAreaService.selectByPrimaryKey(id);
			pArea = sysAreaService.selectByPrimaryKey(area.getParentId());
		}
		model.addAttribute("pArea", pArea)
			.addAttribute("area", area);
		return mode.equals("detail")?"sys/area/area-detail":"sys/area/area-save";
	}
	
	
}
