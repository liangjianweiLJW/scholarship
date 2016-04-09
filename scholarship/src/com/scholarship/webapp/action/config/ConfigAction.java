package com.scholarship.webapp.action.config;

import java.util.ArrayList;
import java.util.List;

import com.scholarship.module.account.Account;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.service.audit.AuditService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class ConfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AuditService auditService;
	
	private String applySwitch;
	private String codesSwitch;
	private String alertInfo;
	
	public String queryApplySwitch(){
		applySwitch = String.valueOf(AppConfig.APPLY);
		return SUCCESS;
	}
	
	public String queryCodesSwitch(){
		codesSwitch = String.valueOf(AppConfig.CODES);
		return SUCCESS;
	}
	
	public String changeApplySwitch(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		
		if(AppConfig.APPLY==0)AppConfig.APPLY=1;
		else AppConfig.APPLY=0;
		applySwitch = String.valueOf(AppConfig.APPLY);
		addActionMessage("已"+(AppConfig.APPLY==0?"关闭":"开启")+"奖/助学金申请提交");
		
		List<String> list = new ArrayList<>();
		list.add(account.getName()+"("+account.getId()+")已"+(AppConfig.APPLY==0?"关闭":"开启")+"奖/助学金申请提交");
		auditService.operator(account.getId(), "修改申请开关", getRequest().getRemoteAddr(),list);
		
		return SUCCESS;
	}
	
	public String changeCodesSwitch(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		
		if(AppConfig.CODES==0)AppConfig.CODES=1;
		else AppConfig.CODES=0;
		codesSwitch = String.valueOf(AppConfig.CODES);
		addActionMessage("已"+(AppConfig.CODES==0?"关闭":"开启")+"验证码");
		
		List<String> list = new ArrayList<>();
		list.add(account.getName()+"("+account.getId()+")已"+(AppConfig.CODES==0?"关闭":"开启")+"验证码");
		auditService.operator(account.getId(), "修改验证码开关", getRequest().getRemoteAddr(),list);
		
		return SUCCESS;
	}
	
	public String queryAlertInfo(){
		alertInfo = AppConfig.ALERT;
		return SUCCESS;
	}
	
	public String changeAlertInfo(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		if(StringUtil.isNotBlank(alertInfo)){
			AppConfig.ALERT = alertInfo;
			addActionMessage("已修改");
			List<String> list = new ArrayList<>();
			list.add(account.getName()+"("+account.getId()+")已修改申请警告信息");
			auditService.operator(account.getId(), "修改警告信息", getRequest().getRemoteAddr(),list);
		}
		return SUCCESS;
	}
	
	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public String getApplySwitch() {
		return applySwitch;
	}

	public String getCodesSwitch() {
		return codesSwitch;
	}

	public void setApplySwitch(String applySwitch) {
		this.applySwitch = applySwitch;
	}

	public void setCodesSwitch(String codesSwitch) {
		this.codesSwitch = codesSwitch;
	}

	public String getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(String alertInfo) {
		this.alertInfo = alertInfo;
	}
	
}
