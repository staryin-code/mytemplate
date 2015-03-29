package com.template.web.shiro;

import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.template.common.constant.Constant;
import com.template.common.exception.BaseException;
import com.template.common.ip.IPSeeker;
import com.template.common.utils.ContextUtil;
import com.template.common.utils.PasswordHash;


public class UserMatcher extends SimpleCredentialsMatcher {

	private static final Logger console = LoggerFactory
			.getLogger(UserMatcher.class);
	
	//public static LoginService loginService;
	
	private static IPSeeker ip = null;   
	
	static{
		String path = SimpleCredentialsMatcher.class.getClassLoader().getResource("/").getPath();
		if(StringUtils.contains(path, "%")){
			try {
				path = URLDecoder.decode(path, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ip = new IPSeeker("qqwry.dat",path);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo info) {
		String userPassword = getUserPassword(authcToken);// 用户输入的密码
		Object accountCredentials = getCredentials(info);//数据库密码
		HttpServletRequest request = ContextUtil.getCurRequest();
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		boolean pass;
		try {
			pass = PasswordHash.validatePassword(userPassword,
					String.valueOf(accountCredentials));
		} catch (NoSuchAlgorithmException e) {
			console.error(e.getMessage());
			throw new BaseException(Constant.PASSWORD_VERIFY_ERROR);
		} catch (InvalidKeySpecException e) {
			console.error(e.getMessage());
			throw new BaseException(Constant.PASSWORD_VERIFY_ERROR);
		}
		if (pass) {
			console.info("hash密码验证成功");
			System.out.println(ip);
			//loginService.insert(new Login(getUserName(authcToken), getHost(request),ip.getIPLocation(getHost(request)).getCountry()+":"+ip.getIPLocation(getHost(request)).getArea()));
			return true;
		} else {
			console.info("hash密码验证失败");
			Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				if(null!=subject.getSession()){
					subject.getSession().removeAttribute(Constant.SESSION_LOGIN_USER);
				}
			}
			throw new IncorrectCredentialsException(Constant.USER_PASSWORD_VERIFY);
		}
	}

	private String getUserPassword(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		return String.valueOf(token.getPassword());
	}
	
	/*private String getHost(HttpServletRequest request){
		return UserController.getHost(request);
	}*/
	
	private String getUserName(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		return token.getUsername();
	}
}
