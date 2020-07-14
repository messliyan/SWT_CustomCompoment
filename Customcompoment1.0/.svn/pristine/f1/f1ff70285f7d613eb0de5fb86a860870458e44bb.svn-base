package JoinCustomwidgets;


import java.util.ArrayList;
import java.util.List;

import JoinCustomwidgets.panel.JoinCompent;

public class Condition extends AbstractCompent {

	public Condition(JoinCompent joinCompent) {
		super(joinCompent);	
	}

	@Override
	protected void intiElements() {
		// TODO Auto-generated method stub	
		elements.add(joinItemElementColumnText1);
		elements.add(joinItemElemenEqual);
		elements.add(joinItemElementColumnText2);
//		elements.add(jointemElementAdd);
		elements.add(joinItemElementDelete);
		elements.add(joinItemElemenDown);
	}		
	@Override
	protected void DownWidgetSelect() {
		// TODO Auto-generated method stub
		super.DownWidgetSelect();

		
		Condition  condition= new Condition(joinCompent);
		
		Conjunction conjunction =new Conjunction(joinCompent);
		conjunction.ListChildCompent().add(this);
		conjunction.add(condition);
		condition.setParentCompent(conjunction);
		int index=parent.getIndex(this);
		parent.removeNotListener(this);
		parent.add(index,conjunction);
		
		setParentCompent(conjunction);
		
	}

}
