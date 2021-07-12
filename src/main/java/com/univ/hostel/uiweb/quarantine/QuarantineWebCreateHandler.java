package com.univ.hostel.uiweb.quarantine;

import com.zitlab.palmyra.cinch.pojo.QueryFilter;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.uiweb.handler.WebCreateHandler;

public class QuarantineWebCreateHandler implements WebCreateHandler{

	public QuarantineWebCreateHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public QueryFilter applyPreFilter(QueryFilter filter) {
		// TODO Auto-generated method stub
		return filter;
	}

	@Override
	public int aclCheck(String subType, String action, Tuple item) {
		// TODO Auto-generated method stub
		return 7;
	}

}
