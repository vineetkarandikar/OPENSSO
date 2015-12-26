package com.open.sso.model;

import java.util.List;


/**
 * This pojo class is for role.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */
public class Role {

	private String roleName;

	private List<String> mappingUrls;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getMappingUrls() {
		return mappingUrls;
	}

	public void setMappingUrls(List<String> mappingUrls) {
		this.mappingUrls = mappingUrls;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", mappingUrls=" + mappingUrls
				+ "]";
	}

}

