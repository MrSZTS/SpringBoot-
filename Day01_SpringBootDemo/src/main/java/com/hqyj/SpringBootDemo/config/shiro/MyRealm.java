package com.hqyj.SpringBootDemo.config.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.ResourceService;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;

//不是一个纯粹的配置类，realm只是（属于）一个组件，所以不用@Configuration
@Component
public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	
	//资源授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			
		String userName = (String) principals.getPrimaryPrincipal();
		User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException("this user name do not exist");
		}
		
		List<Role> roles = roleService.getRolesByUserId(user.getUserId());
		//循环roles将role的信息添加到身份验证器里面
		for (Role role : roles) {
			simpleAuthorizationInfo.addRole(role.getRoleName());
			List<Resource> resources = resourceService.getResourcesByRoleId(role.getRoleId());
			//资源验证器
			for (Resource resource : resources) {
				simpleAuthorizationInfo.addStringPermission(resource.getPermission());
			}
		}		
		return simpleAuthorizationInfo;
	}

	//身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取页面输入的用户名
		String userName = (String) token.getPrincipal();
		//查询数据库查看数据库中是否存在该user
		User user = userService.getUserByUserName(userName);
		if(user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}
		
		//身份验证器，包装用户名和密码		getName()是调用了他的父类，相当于是realm的名字
		return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
	}

}
