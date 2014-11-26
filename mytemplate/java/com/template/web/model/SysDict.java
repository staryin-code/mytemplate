//Powered By if, Since 2014 - 2020

package com.template.web.model;

import java.util.Date;

import com.template.core.base.Entity;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysDict extends Entity {

	private static final long serialVersionUID = 1L;

    private Long id; //id <编号>

    private String createBy; //create_by <创建者>

    private Date createDate; //create_date <创建时间>

    private String delFlag; //del_flag <删除标记>

    private String description; //description <描述>

    private String label; //label <标签名>

    private String remarks; //remarks <备注信息>

    private Integer sort; //sort <排序（升序）>

    private String type; //type <类型>

    private String updateBy; //update_by <更新者>

    private Date updateDate; //update_date <更新时间>

    private String value; //value <数据值>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public String getCreateBy() {
		return this.getString("create_by");
    }
   
    public void setCreateBy(String createBy) {
		this.set("create_by", createBy);
    }

	public Date getCreateDate() {
		return this.getDate("create_date");
    }
   
    public void setCreateDate(Date createDate) {
		this.set("create_date", createDate);
    }

	public String getDelFlag() {
		return this.getString("del_flag");
    }
   
    public void setDelFlag(String delFlag) {
		this.set("del_flag", delFlag);
    }

	public String getDescription() {
		return this.getString("description");
    }
   
    public void setDescription(String description) {
		this.set("description", description);
    }

	public String getLabel() {
		return this.getString("label");
    }
   
    public void setLabel(String label) {
		this.set("label", label);
    }

	public String getRemarks() {
		return this.getString("remarks");
    }
   
    public void setRemarks(String remarks) {
		this.set("remarks", remarks);
    }

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
		this.set("type", type);
    }

	public String getUpdateBy() {
		return this.getString("update_by");
    }
   
    public void setUpdateBy(String updateBy) {
		this.set("update_by", updateBy);
    }

	public Date getUpdateDate() {
		return this.getDate("update_date");
    }
   
    public void setUpdateDate(Date updateDate) {
		this.set("update_date", updateDate);
    }

	public String getValue() {
		return this.getString("value");
    }
   
    public void setValue(String value) {
		this.set("value", value);
    }


}