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
public class AssertInt{    
	public static void hasNonNullInt(Tuple tuple, String... attributes) {
		for(String attrib : attributes){
		if(tuple.hasAttribute(attrib)){
			Integer value = tuple.getAttributeAsInt(attrib);
			if(null == value)
				throw new PdbcException("mandatory parameter " + attrib +" should not be null");
			}
		}
	}
	
	public static void nonNullInt(Tuple tuple, String... attributes) {
		for(String attrib : attributes){
		Integer value = tuple.getAttributeAsInt(attrib);
		if(null == value)
			throw new PdbcException("mandatory parameter " + attrib +" is missing");
		}
	}

	public static void hasNonNullZeroInt(Tuple tuple, String... attributes) {
		for(String attrib : attributes){
		if(tuple.hasAttribute(attrib)){
			Integer value = tuple.getAttributeAsInt(attrib);
			if(null == value)
				throw new PdbcException("mandatory parameter " + attrib +" should not be null");
			
			if(0 == value)
				throw new PdbcException( attrib +" cannot be zero");
			}
		}
	}
	
	public static void nonNullZeroInt(Tuple tuple, String... attributes) {
		for(String attrib : attributes){
			Integer value = tuple.getAttributeAsInt(attrib);
			if(null == value)
				throw new PdbcException("mandatory parameter " + attrib +" is missing");
			if(0 == value)
				throw new PdbcException( attrib +" cannot be Zero");		
		}
	}
}