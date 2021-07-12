package com.zitlab.palmyra.module.usermgmt;

import com.zitlab.palmyra.uiweb.script.PalmyraWebScript
import com.zitlab.palmyra.api2db.script.ScriptHelper
import com.zitlab.palmyra.api2db.pojo.Tuple
import com.zitlab.palmyra.api2db.pojo.TupleFilter
import com.zitlab.palmyra.api2db.script.ScriptHelper;

import groovy.util.logging.Slf4j 

@Slf4j
public class NewUser extends PalmyraWebScript{
	
	public Tuple preAction(Tuple tuple, Tuple dbTuple, String action) {
		if(null != tuple){
			tuple.setAttribute("random", "random");
			tuple.setAttribute("password", "password");
			tuple.setAttribute("locked", 0);
		}
		return tuple;
	}
}