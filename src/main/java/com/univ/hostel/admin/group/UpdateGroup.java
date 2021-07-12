package com.univ.hostel.admin.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zitlab.palmyra.api2db.base.annotations.ApiMapping;
import com.zitlab.palmyra.api2db.base.annotations.WebMapping;
import com.zitlab.palmyra.cinch.pojo.QueryFilter;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.uiweb.handler.WebUpdateHandler;
import com.zitlab.palmyra.uiweb.handler.noop.MenuAuthorizationHandler;
import com.zitlab.palmyra.uiweb.layout.config.PageLayout;
import com.zitlab.palmyra.uiweb.pojo.widget.Container;
import com.zitlab.palmyra.uiweb.pojo.widget.Page;
import com.zitlab.palmyra.uiweb.usermgmt.AclMenuMgmtProcessor;

@ApiMapping("group") @WebMapping("group")
@Component()
public class UpdateGroup extends MenuAuthorizationHandler implements WebUpdateHandler{

	private AclMenuMgmtProcessor menuProcessor;
		
	@Override
	public QueryFilter applyPreFilter(QueryFilter filter) {
		return filter;
	}
	
	@Override
	public Page onProcess(PageLayout layout, Page page, Tuple tuple, String action) {						
		for (Container container : page.getAllContainers().values()) {			
			String value = container.getCustomValue();			
			if (null != value) {
				try {
					menuProcessor.process(value, tuple.getId());					
				} catch (Throwable e) {
	//				logger.error("AclMenuMgmtProcessor is not loaded as the Bean", );
					throw e;
				}
			}
		}
		return page;
	}

	@Autowired	
	public void setMenuProcessor(AclMenuMgmtProcessor menuProcessor) {
		this.menuProcessor = menuProcessor;
	}
}
