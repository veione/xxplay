package com.xxplay.controller.gameCenter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxplay.common.exception.ServiceException;
import com.xxplay.model.dto.page.PageBean;
import com.xxplay.model.persistent.app.AppType;
import com.xxplay.service.gameCenter.IGameCategoryService;

/**
 * 游戏类型Controller层
 * 
 * @Author:chenssy
 * 
 * @date:2016年4月22日
 */
@Controller
@RequestMapping("/gameCatetory")
public class GameCategoryManagerController {
	
	@Resource
	private IGameCategoryService gameCategoryService;
	
	/**
	 * 游戏分类
	 *
	 * @return
	 *
	 * @author:chenssy
	 * @date : 2016年4月22日
	 */
	@RequestMapping("/gameCategoryManager")
	public String gameCategoryManager(){
		return "/game/gameCategory";
	}
	
	/**
	 * 获取游戏分类列表信息
	 *
	 * @param params
	 * @return
	 *
	 * @author:chenssy
	 * @date : 2016年4月22日
	 */
	@RequestMapping("/queryGameCategoyrList")
	@ResponseBody
	public PageBean queryGameCategoyrList(@RequestBody Map<String, String> params){
		PageBean  pageBean = gameCategoryService.queryGameCategoryList(params);
		
		return pageBean;
	}
	
	/**
	 * 更改分类状态
	 *
	 * @param params
	 * @return
	 *
	 * @author:chenssy
	 * @date : 2016年4月23日 上午12:24:17
	 */
	@RequestMapping("/updateGameCategoryStatus")
	@ResponseBody
	public Map<String,Object> updateGameCategoryStatus(@RequestBody Map<String, String> params){
		boolean result = true;
		try {
			AppType appType = new AppType();
			appType.setTypeId(Integer.valueOf(params.get("id")));
			appType.setTypeStatus(params.get("status"));
			gameCategoryService.updateGameCategory(appType);
		} catch (ServiceException e) {
			result = false;
		}
		
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 新增游戏分类
	 *
	 * @return
	 *
	 * @author:chenssy
	 * @date : 2016年4月23日 上午10:06:27
	 */
	@RequestMapping("/addGameCategory")
	public String addGameCategory(){
		return "game/gameCategoryEdit";
	}

}
