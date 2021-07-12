package com.univ.hostel.admin.user;

import org.springframework.stereotype.Component;

import com.zitlab.palmyra.api2db.base.annotations.ApiMapping;

import com.zitlab.palmyra.cinch.pojo.QueryFilter;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.cinch.security.ACLRights;
import com.zitlab.palmyra.uiweb.handler.WebCreateHandler;

@ApiMapping("user")
@Component()
public class NewUser implements WebCreateHandler{

	@Override
	public QueryFilter applyPreFilter(QueryFilter filter) {
		return filter;
	}

	@Override
	public int aclCheck(String subType, String action, Tuple item) {
		return ACLRights.CREATE;
	}

	@Override
	public Tuple preCreate(Tuple tuple) {
		if(null != tuple){
			tuple.setAttribute("random", "random");
			tuple.setAttribute("password", "password");
			tuple.setAttribute("locked", 0);
		}
		return tuple;
	}
}