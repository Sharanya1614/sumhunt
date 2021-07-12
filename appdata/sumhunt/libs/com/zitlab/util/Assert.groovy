package com.zitlab.util;

import com.zitlab.palmyra.api2db.script.PalmyraAppScript
import com.zitlab.palmyra.api2db.script.ScriptHelper
import com.zitlab.palmyra.api2db.pojo.Tuple
import com.zitlab.palmyra.api2db.pojo.TupleFilter
import com.zitlab.palmyra.webservice.security.UnAuthorizedException
import com.zitlab.palmyra.api2db.exception.DataValidationException

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j 

@Slf4j
@CompileStatic
public class Assert{  

	public static boolean isNull(Tuple tuple, String attribute){
		return null == tuple.getAttribute(attribute);
	}

	public static void hasNonNull(Tuple tuple, String... attributes) {
		Object value;
		for(String attrib : attributes){
		if(tuple.hasAttribute(attrib)){
			value = tuple.getAttribute(attrib);
			if(null == value)
				throw new DataValidationException(attrib, 
					"mandatory parameter " + attrib +" should not be null");
			}
		}
	}
	
	public static void nonNull(Tuple tuple, String... attributes) {
		Object value;

		for(String attrib : attributes){
			value = tuple.getAttribute(attrib);
			if(null == value)
				throw new DataValidationException(attrib, 
					"mandatory parameter " + attrib +" should not be null");
		}
	}
}