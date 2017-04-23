package com.alan.sample;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;

import com.alan.sample.model.MenuInfoVO;
import com.alan.sample.model.MenuInfoQuery;

@Path("sample")
public interface SampleService {

	public BaseResponse<List<MenuInfoVO>> findUserList(@FormParam("name")String name ,@FormParam("pageNo") Integer pageNo ,@FormParam("pageSize") Integer pageSize);
	
	public BaseResponse<List<MenuInfoVO>> findUserListByQuery(MenuInfoQuery userQuery);
	
	@Consumes("application/json")
	public BaseResponse<List<MenuInfoVO>> findUserListJSON(MenuInfoQuery userQuery);
}
