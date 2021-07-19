package com.univ.hostel.report.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zitlab.palmyra.api2db.base.annotations.ApiMapping;
import com.zitlab.palmyra.api2db.handler.ActionHandler;
import com.zitlab.palmyra.api2db.service.TupleService;
import com.zitlab.palmyra.cinch.converter.DateConverter;
import com.zitlab.palmyra.cinch.pojo.GenericItem;
import com.zitlab.palmyra.cinch.pojo.Tuple;
import com.zitlab.palmyra.cinch.tuple.dao.NativeQueryDao;
import com.zitlab.palmyra.cinch.tuple.dao.TupleDao;

@ApiMapping("order/report")
@Component
public class OrderHandler implements ActionHandler {

	@Autowired
	private TupleService tupleService;

	@Autowired
	private NativeQueryDao dao;

	@Autowired
	private TupleDao tupleDao;

	@Override
	public int aclCheck(String subType, String action, Tuple item) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object executeAction(String subType, String action, Tuple data) {

		List<GenericItem> studentList = getStudentList();
		List<GenericItem> mealList = getMealAmount("lunch");
		GenericItem meal = mealList.get(0);
		Object mealId = meal.getAttribute("id");
		Object mealAmount = meal.getAttribute("amount");
		Object date = DateConverter.instance().convert(new Date());
		for (GenericItem student : studentList) {
			data = new Tuple();
			data.setType("Orders");
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("studId", student.getAttribute("Student"));
			attributes.put("mealId", mealId);
			attributes.put("mealAmount", mealAmount);
			attributes.put("orderDate", date);
			data.setAttributes(attributes);
			tupleService.save(data);			
		}
		return studentList;

	}

	private List<GenericItem> getStudentList() {
		String query = "select distinct stud_id Student from hostel_food_delivery.quarantine q "
				+ "where end_date is null";
		return dao.list(query);
	}

	private List<GenericItem> getMealAmount(String mealType) {
		String query = "select id,delivery_charge amount from hostel_food_delivery.mst_meal_type mmt "
				+ "where type_name=?";
		return dao.list(query, mealType);

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
