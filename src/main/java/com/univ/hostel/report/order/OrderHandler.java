package com.univ.hostel.report.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zitlab.palmyra.api2db.base.annotations.ApiMapping;
import com.zitlab.palmyra.api2db.handler.ActionHandler;
import com.zitlab.palmyra.api2db.service.TupleService;
import com.zitlab.palmyra.cinch.pojo.FieldList;
import com.zitlab.palmyra.cinch.pojo.QueryFilter;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.cinch.tuple.condition.ConditionBuilder;

@ApiMapping("order/report")
@Component
public class OrderHandler implements ActionHandler{
	
	@Autowired
	private TupleService tupleService;
	
	public OrderHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int aclCheck(String subType, String action, Tuple item) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object executeAction(String subType, String action, Tuple data) {
		QueryFilter filter=new QueryFilter();	
				
		FieldList fieldList = new FieldList();
		
		fieldList.addParentField("studId.name");
//		fieldList.addField("studId.studCode");
//		fieldList.addField("studId.roomNumber");
		fieldList.addField("startDate");
		filter.setFields(fieldList);
		filter.addCondition(new ConditionBuilder().isNull("endDate").isNotNull("startDate").build());
		return tupleService.list("Quarantine", filter);
	}

	@Override
	public void rollback(String subType, String action, Tuple data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback(String subType, String action, List<Tuple> data, List<Object> result, int index) {
		// TODO Auto-generated method stub
		
	}

}
