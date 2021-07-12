package com.zitlab.palmyra.module.usermgmt;

import com.zitlab.palmyra.uiweb.script.PalmyraWebScript
import com.zitlab.palmyra.api2db.script.ScriptHelper
import com.zitlab.palmyra.api2db.pojo.Tuple
import com.zitlab.palmyra.api2db.pojo.TupleFilter
import com.zitlab.palmyra.uiweb.usermgmt.AclMenuMgmtProcessor;
import com.zitlab.palmyra.api2db.script.ScriptHelper;
import com.zitlab.palmyra.uiweb.pojo.widget.Container;
import com.zitlab.palmyra.uiweb.layout.config.*;
import com.zitlab.palmyra.uiweb.pojo.widget.*;

import groovy.util.logging.Slf4j 

@Slf4j
public class NewGroup extends PalmyraWebScript{
	
	public Page onPageProcess(PageLayout layout, Page page, Tuple tuple, Tuple dbTuple, String action) {
		ScriptHelper helper = getHelper();				
		for (Container container : page.getAllContainers().values()) {			
			String value = container.getCustomValue();			
			if (null != value) {
				try {			
					AclMenuMgmtProcessor processor = helper.getBean(AclMenuMgmtProcessor.class);
					processor.process(value, tuple.getId());					
				} catch (Throwable e) {
					log.error("AclMenuMgmtProcessor is not loaded as the Bean", );
					throw e;
				}
			}
		}
		return page;
	}
}