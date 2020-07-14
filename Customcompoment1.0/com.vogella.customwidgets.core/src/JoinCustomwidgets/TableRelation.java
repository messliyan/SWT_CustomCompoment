package JoinCustomwidgets;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import JoinCustomwidgets.additional.PromotShell;
import JoinCustomwidgets.elements.IDrawElement;
import JoinCustomwidgets.elements.JoinItemElementDelete;
import JoinCustomwidgets.elements.JoinItemElementJoin;
import JoinCustomwidgets.elements.JoinItemElementTableText;
import JoinCustomwidgets.elements.JointemElementAdd;
import JoinCustomwidgets.panel.JoinCompent;

public class TableRelation extends AbstractCompent {
	
	public TableRelation(JoinCompent joinCompent) {
		super(joinCompent);	
	}

	@Override
	protected void intiElements() {
		// TODO Auto-generated method stub
		
		elements.add(joinItemElemetTreeExpose);
		elements.add(JoinItemElementTableText1);
		elements.add(JoinItemElementJoin);
		elements.add(JoinItemElementTableText2);	
		elements.add(jointemElementAdd)	;
		elements.add(joinItemElementDelete);
	}		
}
