package JoinCustomwidgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;


import JoinCustomwidgets.additional.PromotShell;
import JoinCustomwidgets.elements.IDrawElement;
import JoinCustomwidgets.elements.JoinItemElementDelete;
import JoinCustomwidgets.elements.JointemElementAdd;
import JoinCustomwidgets.panel.JoinCompent;

public class Root extends AbstractCompent {
	
	public Root(JoinCompent joinCompent) {
		super(joinCompent);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void intiElements() {
		// TODO Auto-generated method stub
		elements.add(jointemElementAdd);
		elements.add(joinItemElementDelete);
	}
	@Override
	protected void addWidgetSelect() {
		// TODO Auto-generated method stub
		TableRelation  tableRelation =new TableRelation(joinCompent);
		Condition  condition= new Condition(joinCompent);
		tableRelation.add(condition);
		add(tableRelation);
	}
	@Override
	protected void delectWidgetSelect() {
		// TODO Auto-generated method stub
		delectAllChild(this);
		joinCompent.redraw();
		
	}
	private void delectAllChild(AbstractCompent adb) {
		 List<AbstractCompent> list=adb.ListChildCompent();
		 for ( int i = 0; i < list.size(); i++) {  
			 adb.remove(list.get(i));
				i--; // 因为位置发生改变，所以必须修改i的位置  	
		} 
	}

}
