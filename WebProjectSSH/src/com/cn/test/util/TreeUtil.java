package com.cn.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.cn.test.util.StringUtil;
import com.cn.test.util.SystemConstantUtil;
import com.hichina.caba.authority.model.Menu;
import com.hichina.caba.system.model.Organization;
import com.hichina.caba.system.model.User;

public class TreeUtil {

	/* 选择树类型：部门树 */
	public static final String SELECT_TREE_TYPE_organization = "2";
	/* 选择树类型：菜单树 */
	public static final String SELECT_TREE_TYPE_menu = "1";
	
	/* 选择树节点个数：单选 */
	public static final String SELECT_TREE_NODE_NUM_ONE = "1";
	/* 选择树节点个数：多选 */
	public static final String SELECT_TREE_NODE_NUM_MORE = "2";

	
	/**
	 * 方法描述：创建树 备 注: 创 建 人: ZhaoYanLei 创建日期: 2011-11-7下午02:48:38

	 */
	public String createTreeForOrganization(String userId, String doType,

			
			String contextPath
			//, MenuService treeNodeManager
			,List certFileCategorys) {
		StringBuffer rtnTreeStr = new StringBuffer("");
		// doType=("DocumentCategoryAdmin");

		try {
			String treeSuperRootId = "-101";

			String treeRootId = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_ID;// "-1";
			String treeRootName = SystemConstantUtil.Organization_TRER_ROOT_NAME;// "组织机构";
			String treeRootHerl = "#";

			String nodeTarget = "_self";// "orgMainFrame";

			/* 根节点url链接: */
			if (doType != null
					&& doType.equalsIgnoreCase("OrganizationAdmin")) {
				// treeRootHerl = "#";
				treeRootHerl = contextPath + "/searchOrganization.action"
						+ "?parentId=" + treeRootId + "&parentName="
						+ treeRootName;
			}

			rtnTreeStr.append("d.add('" + treeRootId + "'," + treeSuperRootId
					+ ",'" + treeRootName + "','" + treeRootHerl + "','"
					+ treeRootName + "','" + nodeTarget + "');");

			if (certFileCategorys != null) {

				for (int useOrganizeLength = 0; useOrganizeLength < certFileCategorys
						.size(); useOrganizeLength++) {

					Organization category = (Organization) certFileCategorys
							.get(useOrganizeLength);

					String treeNodeHerl = "#";

					/* 分支节点url链接: */
					if (doType != null
							&& doType.equalsIgnoreCase("OrganizationAdmin")) {
						treeNodeHerl = contextPath
								+ "/searchOrganization.action" + "?parentId="
								+ category.getOrganizationId() + "&parentName="
								+ category.getOrganizationName().trim();
					}

					// d.add('<%=organize.getId()%>','<%=(this.isExistsParentOrganize(organize,useOrganizeList))?organize.getParentId():-1%>','<%=organize.getName()%>','<%=contextPath%>/console/certfile.do?method=viewCertFileCategory&id=<%=organize.getId()%>','<%=organize.getName()%>','orgMainFrame');
					String parentId = (this.isExistsParentNode(category,
							certFileCategorys)) ? category.getParentId() + ""
							: treeSuperRootId;
					rtnTreeStr.append("d.add('" + category.getOrganizationId()
							+ "','" + parentId + "','"
							+ category.getOrganizationName().trim() + "','" + treeNodeHerl
							+ "','" + category.getOrganizationName().trim() + "','"
							+ nodeTarget + "');");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtnTreeStr.toString();
	}
	
	public String createTreeForMenu(String userId, String doType,
			String contextPath		
			,List menuList) {
		StringBuffer rtnTreeStr = new StringBuffer("");
		// doType=("LeftFrameTree");

		try {
			String treeSuperRootId = "-101";

			String treeRootId = SystemConstantUtil.LeftFrame_TRER_ROOT_ID;// "-1";
			String treeRootName = SystemConstantUtil.LeftFrame_TRER_ROOT_NAME;// "系统菜单";
			String treeRootHerl = "#";

			String nodeTarget = "mainFrame";// "_self";

			/* 根节点url链接: */
			if (doType != null
					&& doType.equalsIgnoreCase("LeftFrameTree")) {
				// treeRootHerl = "#";
				treeRootHerl = contextPath + "/homepage_left.action"
						+ "?parentMenuId=" + treeRootId + "&parentMenuName="
						+ treeRootName;
			}

			rtnTreeStr.append("d.add('" + treeRootId + "'," + treeSuperRootId
					+ ",'" + treeRootName + "','" + treeRootHerl + "','"
					+ treeRootName + "','" + "_self" + "');");

			if (menuList != null) {

				for (int useOrganizeLength = 0; useOrganizeLength < menuList
						.size(); useOrganizeLength++) {

					Menu treeNode = (Menu) menuList
							.get(useOrganizeLength);
					
					String treeNodeHerl = "#";

					/* 分支节点url链接: */
					if (doType != null
							&& doType.equalsIgnoreCase("LeftFrameTree")) {					
						
						if(treeNode.getMenuUrl()!=null && (!treeNode.getMenuUrl().trim().equalsIgnoreCase("#"))){
							treeNodeHerl = contextPath
//							+ "/searchOrganization.action" + "?parentId="
							+ "/" + treeNode.getMenuUrl()+ "?parentMenuId="
							+ treeNode.getMenuId() + "&parentMenuName="
							+ treeNode.getMenuName().trim();
						}

					}

					// d.add('<%=organize.getId()%>','<%=(this.isExistsParentOrganize(organize,useOrganizeList))?organize.getParentId():-1%>','<%=organize.getName()%>','<%=contextPath%>/console/certfile.do?method=viewCertFileCategory&id=<%=organize.getId()%>','<%=organize.getName()%>','orgMainFrame');
					
					
					String parentId = (this.isExistsParentNode(treeNode,
							menuList)) ? treeNode.getParentMenuId() + ""
							: treeSuperRootId;
					rtnTreeStr.append("d.add('" + treeNode.getMenuId()
							+ "','" + parentId + "','"
							+ treeNode.getMenuName().trim() + "','" + treeNodeHerl
							+ "','" + treeNode.getMenuName().trim() + "','"
							+ nodeTarget + "');");
					}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		//d.add('-1',-101,'组织机构','#','组织机构','_self');d.add('1','0','系统管理','#','系统管理','_self');
		//d.add('2','1','后台用户管理','#','后台用户管理','_self');d.add('3','1','机构管理','#','机构管理','_self');
		//d.add('4','1','角色管理','#','角色管理','_self');d.add('5','1','参数设置','#','参数设置','_self');
		//d.add('6','1','日志管理','#','日志管理','_self');d.add('7','8','系统操作管理','#','系统操作管理','_self');
		//d.add('8','0','项目实施','#','项目实施','_self');d.add('9','8','菜单管理','#','菜单管理','_self');
		//d.add('10','1','查询框架','#','查询框架','_self');d.add('11','-1','根菜单','#','根菜单','_self');
		
		//	return " mytree.add('00000000000000000000000000000000',-1,'系统菜单','javascript:test()'); mytree.add('2','00000000000000000000000000000000','22','',1,false); mytree.add('1','00000000000000000000000000000000','11','',1,false); mytree.add('3','1','12','',1,false);";
				
		return rtnTreeStr.toString();
	}
	
	
	/**
	 * 方法描述：创建选择树 备 注: 
	 * 创 建 人: ZhaoYanLei
	 *  创建日期: 2011-11-28 下午02:48:38
	 * 
	 * @param userId
	 * @param doType
	 * @param contextPath

	 * @param certFileCategorys
	 * @return
	 */

	public String createSelectTreeForMenu(String userId, String treeType,
			String contextPath,List certFileCategorys) {
		StringBuffer rtnTreeStr = new StringBuffer("");
		// doType=("DocumentCategoryAdmin");

		/*
		 * tree.add('00000000000000000000000000000000',-1,'类别','javascript:test()');
		 * tree
		 * .add('38','00000000000000000000000000000000','类别0101','',1,false);
		 * tree
		 * .add('35','00000000000000000000000000000000','乱码类别01','',1,false);
		 * tree
		 * .add('25','00000000000000000000000000000000','catgegory1','',1,false);
		 * tree
		 * .add('39','00000000000000000000000000000000','类别0102','',1,false);
		 * tree
		 * .add('26','00000000000000000000000000000000','catgegory2','',1,false);
		 * tree.add('36','00000000000000000000000000000000','测试','',1,false);
		 * tree.add('27','25','catgegory1_1','',1,false);
		 * tree.add('31','25','catgegory1_3','',1,false);
		 * tree.add('28','26','catgegory2_1','',1,false);
		 * tree.add('44','35','test中文','',1,false);
		 * tree.add('40','39','类别010201','',1,false);
		 */
		try {
			String treeSuperRootId = "-1";// "-101";

			String treeRootId = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_ID;// "-1";
			String treeRootName = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_NAME;// "公文根类别";
			String treeRootHerl = "#";

			String nodeTarget = "_self";// "orgMainFrame";

			/* 根节点url链接: */
			if (treeType != null
					&& treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
				// treeRootHerl = "#";
				treeRootHerl = contextPath + "/searchDocCategory.action"
						+ "?parentId=" + treeRootId + "&parentName="
						+ treeRootName;
			}

			if ((treeType != null)
					&& (treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu))) {
				treeRootName = "系统菜单";
			}

			// rtnTreeStr.append( "d.add('" + treeRootId + "',"+ treeSuperRootId
			// +",'" + treeRootName
			// + "','" + treeRootHerl + "','" + treeRootName + "','"+ nodeTarget
			// +"');");

			treeRootId = "00000000000000000000000000000000";

			rtnTreeStr.append("mytree.add('" + treeRootId + "',"
					+ treeSuperRootId + ",'" + treeRootName
					+ "','javascript:test()');");

			if (certFileCategorys != null) {

				for (int useOrganizeLength = 0; useOrganizeLength < certFileCategorys
						.size(); useOrganizeLength++) {

					Menu category = (Menu) certFileCategorys
							.get(useOrganizeLength);

					String treeNodeHerl = "#";

					/* 分支节点url链接: */
					if (treeType != null
							&& treeType
									.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
						treeNodeHerl = contextPath
								+ "/searchDocCategory.action" + "?parentId="
								+ category.getMenuId() + "&parentName="
								+ category.getMenuName().trim();
					}

					// d.add('<%=organize.getId()%>','<%=(this.isExistsParentOrganize(organize,useOrganizeList))?organize.getParentId():-1%>','<%=organize.getName()%>','<%=contextPath%>/console/certfile.do?method=viewCertFileCategory&id=<%=organize.getId()%>','<%=organize.getName()%>','orgMainFrame');
					String parentId = (this.isExistsParentNode(category,
							certFileCategorys)) ? category.getParentMenuId() + ""
							: treeSuperRootId;

					// rtnTreeStr.append("d.add('" + category.getCategoryId() +
					// "','"
					// + parentId + "','" + category.getCategoryName() + "','"
					// + treeNodeHerl + "','" + category.getCategoryName()
					// + "','"+ nodeTarget +"');");

					if ((parentId != null)
							&& ((parentId.trim().equalsIgnoreCase("-1"))||(parentId.trim().equalsIgnoreCase("0")) )) {
						parentId = treeRootId;
					}
					rtnTreeStr.append(" mytree.add('" + category.getMenuId()
							+ "','" + parentId + "','"
							+ category.getMenuName().trim() + "','',1,false);");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtnTreeStr.toString();
	}

	/**
	 * 方法描述：创建选择树 备 注: 
	 * 创 建 人: ZhaoYanLei
	 *  创建日期: 2011-11-28 下午02:48:38
	 * 
	 * @param userId
	 * @param doType
	 * @param contextPath

	 * @param certFileCategorys
	 * @return
	 */

	public String createSelectTreeForOrgUser(String userId, String treeType,
			String contextPath,List<Organization> objList,Map<String ,List> orgUserMap) {
		StringBuffer rtnTreeStr = new StringBuffer("");
		
		try {
			String treeSuperRootId = "-1";// "-101";

			String treeRootId = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_ID;// "-1";
			String treeRootName = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_NAME;// "公文根类别";
			String treeRootHerl = "#";

			String nodeTarget = "_self";// "orgMainFrame";

			/* 根节点url链接: */
			if (treeType != null
					&& treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
				// treeRootHerl = "#";
				treeRootHerl = contextPath + "/searchDocCategory.action"
						+ "?parentId=" + treeRootId + "&parentName="
						+ treeRootName;
			}

			if ((treeType != null)
					&& (treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu))) {
				treeRootName = "部门人员";
			}

			// rtnTreeStr.append( "d.add('" + treeRootId + "',"+ treeSuperRootId
			// +",'" + treeRootName
			// + "','" + treeRootHerl + "','" + treeRootName + "','"+ nodeTarget
			// +"');");

			treeRootId = "00000000000000000000000000000000";

			rtnTreeStr.append("mytree.add('" + treeRootId + "',"
					+ treeSuperRootId + ",'" + treeRootName
					+ "','javascript:test()');");

			if (objList != null) {

				for (int useOrganizeLength = 0; useOrganizeLength < objList
						.size(); useOrganizeLength++) {

					Organization obj = (Organization) objList
							.get(useOrganizeLength);

					String treeNodeHerl = "#";

					/* 分支节点url链接: */
					if (treeType != null
							&& treeType
									.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
						treeNodeHerl = contextPath
								+ "/searchDocCategory.action" + "?parentId="
								+ obj.getOrganizationId() + "&parentName="
								+ obj.getOrganizationName().trim();
					}

					// d.add('<%=organize.getId()%>','<%=(this.isExistsParentOrganize(organize,useOrganizeList))?organize.getParentId():-1%>','<%=organize.getName()%>','<%=contextPath%>/console/certfile.do?method=viewCertFileCategory&id=<%=organize.getId()%>','<%=organize.getName()%>','orgMainFrame');
					String parentId = (this.isExistsParentNode(obj,
							objList)) ? obj.getParentId() + ""
							: treeSuperRootId;

					// rtnTreeStr.append("d.add('" + category.getCategoryId() +
					// "','"
					// + parentId + "','" + category.getCategoryName() + "','"
					// + treeNodeHerl + "','" + category.getCategoryName()
					// + "','"+ nodeTarget +"');");

					if ((parentId != null)
							&& ((parentId.trim().equalsIgnoreCase("-1"))||(parentId.trim().equalsIgnoreCase("0")) )) {
						parentId = treeRootId;
					}
					rtnTreeStr.append(" mytree.add('" + obj.getOrganizationId()
							+ "','" + parentId + "','"
							+ obj.getOrganizationName().trim() + "','',1,false);");
					
					if(orgUserMap!=null){
						List<User> userList = orgUserMap.get(obj.getOrganizationId()+"");
						if(userList!=null){
							for(User user:userList){
								rtnTreeStr.append(" mytree.add('userId_" + user.getUserId()
										+ "','" + obj.getOrganizationId() + "','"
										+ user.getUserName().trim() + "','',1,false);");
							}
						}
					}
					

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtnTreeStr.toString();
	}

	public String createSelectTreeForUser(String userId, String treeType,
			String contextPath,List<User> objList) {
		StringBuffer rtnTreeStr = new StringBuffer("");
		
		try {
			String treeSuperRootId = "-1";// "-101";

			String treeRootId = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_ID;// "-1";
//			String treeRootName = SystemConstantUtil.DOC_CATEGORY_TRER_ROOT_NAME;// "公文根类别";
			String treeRootName = "人员列表";
			String treeRootHerl = "#";

			String nodeTarget = "_self";// "orgMainFrame";

			/* 根节点url链接: */
			if (treeType != null
					&& treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
				// treeRootHerl = "#";
				treeRootHerl = contextPath + "/searchDocCategory.action"
						+ "?parentId=" + treeRootId + "&parentName="
						+ treeRootName;
			}

			if ((treeType != null)
					&& (treeType
							.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu))) {
				treeRootName = "人员列表";
			}

			// rtnTreeStr.append( "d.add('" + treeRootId + "',"+ treeSuperRootId
			// +",'" + treeRootName
			// + "','" + treeRootHerl + "','" + treeRootName + "','"+ nodeTarget
			// +"');");

			treeRootId = "00000000000000000000000000000000";

			rtnTreeStr.append("mytree.add('" + treeRootId + "',"
					+ treeSuperRootId + ",'" + treeRootName
					+ "','javascript:test()');");

			if (objList != null) {

				for (int useOrganizeLength = 0; useOrganizeLength < objList
						.size(); useOrganizeLength++) {

					User obj = (User) objList
							.get(useOrganizeLength);

					String treeNodeHerl = "#";

					/* 分支节点url链接: */
					if (treeType != null
							&& treeType
									.equalsIgnoreCase(TreeUtil.SELECT_TREE_TYPE_menu)) {
						treeNodeHerl = contextPath
								+ "/searchDocCategory.action" + "?parentId="
								+ obj.getUserId() + "&parentName="
								+ obj.getUserName().trim();
					}

//					String parentId = (this.isExistsParentNode(obj,
//							objList)) ? obj.getParentId() + ""
//							: treeSuperRootId;
					String parentId = treeSuperRootId;

					// rtnTreeStr.append("d.add('" + category.getCategoryId() +
					// "','"
					// + parentId + "','" + category.getCategoryName() + "','"
					// + treeNodeHerl + "','" + category.getCategoryName()
					// + "','"+ nodeTarget +"');");

					if ((parentId != null)
							&& ((parentId.trim().equalsIgnoreCase("-1"))||(parentId.trim().equalsIgnoreCase("0")) )) {
						parentId = treeRootId;
					}
					rtnTreeStr.append(" mytree.add('" + obj.getUserId()
							+ "','" + parentId + "','"
							+ obj.getUserName().trim() + "','',1,false);");
										
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtnTreeStr.toString();
	}

	/**
	 * 方法描述：判断当前结点的父结点是否存在,若不存在,则直接在站点根下显示 备 注: 创 建 人: ZhaoYanLei 创建日期:
	 * 2011-11-7 下午02:46:50
	 * 
	 * @param currentOrganize
	 * @param organizeList
	 * @return
	 */
	private boolean isExistsParentNode(Organization currentOrganize,
			List<Organization> organizeList) {
		for (int i = 0; i < organizeList.size(); i++) {
			if (currentOrganize.getOrganizationId().equals(
					organizeList.get(i).getOrganizationId())) {
				return true;
			}
		}
		return false;
	}

	private boolean isExistsParentNode(Menu currentOrganize,
			List<Menu> organizeList) {
		for (int i = 0; i < organizeList.size(); i++) {
			if (currentOrganize.getMenuId().equals(
					organizeList.get(i).getMenuId())) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 方法描述：<b>显示菜单列表复选树.</b></br>
	 * 备          注: </br>
	 * 创  建   人: yanlei.zhao</br>
	 * 创建日期: 2012-1-6</br>
	 * @param topMenuList
	 * @param menuList
	 * @param userMenuList
	 * @return
	 */
	
	public static StringBuffer createTreeForMenuAuthority(List<Menu> topMenuList,List<Menu> menuList,List<Menu> userMenuList){
		StringBuffer menuSb = new StringBuffer("");
		
		if(topMenuList!=null){
			String inputGrantMenuId = "grantMenuId";
			for(Menu topMenu:topMenuList){
				
				String inputChecked =  isGrantMenuToUser(topMenu.getMenuId()+"", userMenuList)?" checked=\"checked\" ":"";
				
				menuSb.append("<div class=\"perm-layout-1\">");
				menuSb.append("<label><input value=\""+ topMenu.getMenuId() +"\" type=\"checkbox\" name=\""+ inputGrantMenuId +"\" "+ inputChecked +" />"+ topMenu.getMenuName() +"</label>");
	//			
//				List<Menu> childrenMenuList = this.getMenuService().listMenuChildrenByList(menuList,topMenu);
//				
//				if(childrenMenuList!=null){
//					menuSb.append("		<div class=\"perm-layout-2\">");
//					for(Menu childrenMenu:childrenMenuList){
//						String inputCheckedChildren =  isGrantMenuToUser(childrenMenu.getMenuId()+"", userMenuList)?" checked=\"checked\" ":"";
//						menuSb.append("	&nbsp;&nbsp;&nbsp;&nbsp;	<label><input value=\""+ childrenMenu.getMenuId() +"\" type=\"checkbox\" "+ inputCheckedChildren +" name=\""+ inputGrantMenuId +"\"/>"
//								+ childrenMenu.getMenuName()	+"</label><br/>");
//						
//					}
//					menuSb.append("		</div>");
//				}
				
				String childTreeStr = createChildTreeForMenuAuthority(topMenu,menuList, userMenuList,2);
				if(childTreeStr!=null){
					menuSb.append(childTreeStr);
				}
				
				menuSb.append("</div>");
			}
		}
		
		return menuSb;
	}
	
	
	private static String createChildTreeForMenuAuthority(Menu topMenu,List<Menu> menuList,List<Menu> userMenuList ,int menuLayer){
		
		StringBuffer menuSb = new StringBuffer("");
		
		List<Menu> childrenMenuList = listMenuChildrenByList(menuList,topMenu);
		String inputGrantMenuId = "grantMenuId";
		if(childrenMenuList!=null){
//			menuSb.append("		<div class=\"perm-layout-2\">");
			menuSb.append("		<div class=\"perm-layout-"+ (1+ menuLayer) +"\">");
			
			StringBuffer nullStrSb = new StringBuffer("");
			
			for(int i=0;i<menuLayer;i++){
				nullStrSb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			
			for(Menu childrenMenu:childrenMenuList){
				String inputCheckedChildren =  isGrantMenuToUser(childrenMenu.getMenuId()+"", userMenuList)?" checked=\"checked\" ":"";
								
//			    menuSb.append("	&nbsp;&nbsp;&nbsp;&nbsp;");
				menuSb.append(nullStrSb.toString());
				
				menuSb.append("<label><input value=\""+ childrenMenu.getMenuId() +"\" type=\"checkbox\" "+ inputCheckedChildren +" name=\""+ inputGrantMenuId +"\"/>"
						+ childrenMenu.getMenuName()	+"</label><br/>");
				
				List<Menu> childMenuList = listMenuChildrenByList(menuList,childrenMenu);
				if(childMenuList!=null){
					menuSb.append(createChildTreeForMenuAuthority(childrenMenu,menuList, userMenuList,(menuLayer+1)));
				}				
			}
			menuSb.append("		</div>");
		}
		return menuSb.toString();
	}
	
	private static boolean isGrantMenuToUser(String menuId,List<Menu> userMenuList){
		boolean rtnValue = false;
		
		if(userMenuList!=null && !StringUtil.isNullOrEmpty(menuId)){
			for(Menu menu:userMenuList){
				if(menu.getMenuId().intValue()==Integer.parseInt(menuId)){
					rtnValue = true;
					break;
				}
			}
		}
		
		return rtnValue;
	}
	
	/**
	 * @param menus
	 * @return
	 */
	private static List<Menu> listMenuChildrenByList(List<Menu> menus,Menu topMenu)
	{
		HashMap<Integer, Menu> menusHashMap = new HashMap<Integer, Menu>();
		List<Menu> childrenMenuTrees = new ArrayList<Menu>();
		if(null != menus)
		{
			for (int i = 0; i < menus.size(); i++)
			{
				if(menus.get(i).getParentMenuId() != null && menus.get(i).getParentMenuId().intValue()==topMenu.getMenuId().intValue())
				{
					
					childrenMenuTrees.add(menus.get(i));
				}
				
			}
		}
		return childrenMenuTrees;
	}
}
