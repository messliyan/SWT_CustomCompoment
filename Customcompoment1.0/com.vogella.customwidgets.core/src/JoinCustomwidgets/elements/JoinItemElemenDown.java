package JoinCustomwidgets.elements;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.vogella.Activator;

import JoinCustomwidgets.AbstractCompent;
import JoinCustomwidgets.additional.JoinContant;
import JoinCustomwidgets.panel.JoinCompent;


public class JoinItemElemenDown extends IDrawElement {

	public JoinItemElemenDown(JoinCompent joinCompent) {
		super(joinCompent);
		// TODO Auto-generated constructor stub
	}


	private Image image_Down=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/down.png");
	private Image image_Down2=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/down2.png");

	
	@Override
	public Point paint(Point offset, GC gc) {
		
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = offset.x;
		bounds.y = offset.y;
		bounds.height = 27;
		bounds.width = image_Down.getBounds().width;
		
		setBounds(bounds);
		
		Point center = new Point(0, 0);
		center.x = bounds.x + bounds.width/2;
		center.y = bounds.y + bounds.height/2;
		
		Point size = new Point(image_Down.getBounds().width, 
				image_Down.getBounds().height);
		
		int imagex = center.x - size.x/2;
		int imagey = center.y - size.y/2;
		if (MouseOn) {
			gc.drawImage(image_Down2, imagex, imagey);
		}else {
			gc.drawImage(image_Down, imagex, imagey);
		}
		
		Point maxSzie = new Point(bounds.width, bounds.height);
		
		return maxSzie;
	}
}
