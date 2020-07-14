package JoinCustomwidgets;

import java.util.List;

import JoinCustomwidgets.panel.JoinCompent;

public class Conjunction extends AbstractCompent{
	public Conjunction(JoinCompent joinCompent) {
		super(joinCompent);	
	}
	@Override
	protected void intiElements() {
		// TODO Auto-generated method stub	
		
		elements.add(joinItemElemetTreeExpose);
		elements.add(JoinItemElementAnd);
		elements.add(jointemElementAdd)	;
		elements.add(joinItemElementDelete);
	}		
}
