package com.zitlab.util;

import com.zitlab.palmyra.api2db.pojo.Tuple
import com.zitlab.palmyra.api2db.pojo.TupleFilter
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j 

@CompileStatic
@Slf4j
public final class TupleUtil{
	public static void setAttributes(Tuple source, Tuple target, String... attributes){
		for(String attrib: attributes){
			target.setAttribute(attrib, source.getAttribute(attrib));		
		}
	}

	public static void setAttributesIfcontains(Tuple source, Tuple target, String... attributes){
		for(String attrib: attributes){
			log.info("processing attribute {}", attrib);
			if(source.hasAttribute(attrib))
				log.info("setting attribute {}", attrib);
				target.setAttribute(attrib, source.getAttribute(attrib));
		}
	}

	public static void setAttribute(Tuple source, String srcAttrib, Tuple target, String tgtAttrib){
		target.setAttribute(tgtAttrib, source.getAttribute(srcAttrib));
	}
}