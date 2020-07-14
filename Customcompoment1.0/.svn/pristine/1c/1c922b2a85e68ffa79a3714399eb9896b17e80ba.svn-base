package org.eclipse.swt.examples.customcompoment.fiowlayout.views;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import JoinCustomwidgets.*;
import JoinCustomwidgets.panel.JoinCompent;
public class JoinPart extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.swt.examples.customcompoment.fiowlayout.views.SampleView";

	@Inject IWorkbench workbench;
	
	 
	private List<AbstractCompent> compents=new ArrayList<>();

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		parent.setBackground(SWTResourceManager.getColor(255,255,255));
		//DOUBLE_BUFFERED 双缓冲 避免画布闪烁
		JoinCompent joinCompent = new JoinCompent(parent,  SWT.DOUBLE_BUFFERED);
		Root allRoot=new Root(joinCompent);
			
		compents.add(allRoot);
		joinCompent.setAbstractCompent(compents);
//		checkbox.setText("Custom SWT check button");
//
//		checkbox.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
//		checkbox.setCenterColor(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
//		checkbox.setCheckColor(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
	
		Button checkButton = new Button(parent, SWT.NONE);
		checkButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println(joinCompent.getAllSql());
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
		
	}

	
	@Override
	public void setFocus() {
		
	}
}
