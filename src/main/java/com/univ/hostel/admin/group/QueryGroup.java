package com.univ.hostel.admin.group;

import org.springframework.stereotype.Component;

import com.zitlab.palmyra.api2db.base.annotations.ApiMapping;
import com.zitlab.palmyra.cinch.pojo.QueryFilter;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.uiweb.handler.WebViewHandler;
import com.zitlab.palmyra.uiweb.layout.config.PageLayout;
import com.zitlab.palmyra.uiweb.pojo.widget.Page;

@ApiMapping("group")
@Component()
public class QueryGroup implements WebViewHandler{

	@Override
	public int aclCheck(String subType, String action, Tuple item) {
		return 15;
	}

	@Override
	public QueryFilter applyPreFilter(QueryFilter filter) {
		return filter;
	}

	@Override
	public Page preCreate(PageLayout layout, Page page, Tuple tuple, String action) {
		return page;
	}

	@Override
	public Page postCreate(PageLayout layout, Page page, Tuple tuple, String action) {
		return page;
	}
	
}
