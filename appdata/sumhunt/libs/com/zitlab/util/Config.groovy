package com.zitlab.util;

import com.zitlab.palmyra.api2db.script.PalmyraAppScript
import com.zitlab.palmyra.api2db.script.ScriptHelper
import com.zitlab.palmyra.api2db.pojo.Tuple
import com.zitlab.palmyra.api2db.pojo.TupleFilter
import com.zitlab.palmyra.webservice.security.UnAuthorizedException
import com.zitlab.palmyra.api2db.exception.PdbcException

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j 

@Slf4j
@CompileStatic
public class Config{  
	private ScriptHelper helper;

	public Config(ScriptHelper helpr){
		this.helper = helpr;
	}

	public int getIntProperty(String key, int defValue){
		String val = helper.getAppConfig(key);
		if(null == val){
			return defValue;
		}

		try{
			return Integer.parseInt(val);
		}catch(Throwable e){
			return defValue;
		}
	}

	public String getProperty(String key, String defValue){
		String val = helper.getAppConfig(key);
		return null != val ? val : defValue;
	}
}