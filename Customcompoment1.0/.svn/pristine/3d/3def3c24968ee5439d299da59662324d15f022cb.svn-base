package JoinCustomwidgets.elements;


import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

import JoinCustomwidgets.additional.JoinContant;
import JoinCustomwidgets.panel.JoinCompent;

public abstract  class IDrawElement {
	
	protected String text= "";
	
	protected Combo combo;
	protected int comboMinWidth = 100;
	
	
	protected Color color_Empty_bk= SWTResourceManager.getColor(225, 241, 253);
	protected Color color_Empty_fk= SWTResourceManager.getColor(209, 224, 234);
	protected JoinCompent joinCompent;
	public 	IDrawElement(JoinCompent joinCompent){
		this.joinCompent=joinCompent;
		
	}
	/**
	 * 前景色
	 */
	protected Color color_fk;
	
	protected Rectangle bounds;
	
	//点击之后绘制发生变化
	protected Boolean MouseOn=false;
	
	public abstract Point paint(Point offset, GC gc);
	 
	public boolean belong(Point mouseLocation) {
			Rectangle bounds = getBounds();
			if(bounds == null) {
				return false;
			}
			if(bounds.contains(mouseLocation)) {
				return true;
			}
			return false;
	};
	
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return bounds;
	}


	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	/**
	 *点击之后画的不一样了
	 * @param on
	 */
	public  void setMouseOn(Boolean on) {
		MouseOn=on;
	}

	public boolean isMouseOn() {
		return MouseOn;
	}


	public void mouseDownWithCombo() {
		// TODO Auto-generated method stub

		combo = joinCompent.createCombo();
		intiombo();

		bounds.width = bounds.width < comboMinWidth ? comboMinWidth : bounds.width;
		combo.setBounds(bounds);
		
		combo.setText(text);
		combo.setFocus();
		combo.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				setText(combo.getText());
				joinCompent.redraw();
			}
		});
		
		combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		joinCompent.redraw();
	}
	
	public void setText(String text) {
		this.text = text;
		joinCompent.redraw();
	}
	/**
	 * 初始化combo 
	 */
	protected  void intiombo() {
		
	}
	public String getText() {
		return text;
	}
}
