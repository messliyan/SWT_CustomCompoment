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


public class JoinItemElemetTreeExpose extends IDrawElement {

	public JoinItemElemetTreeExpose(JoinCompent joinCompent) {
		super(joinCompent);
		// TODO Auto-generated constructor stub
	}

	private Image image_Expose=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/expose.png");
	private Image image_Merge=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/merge.png");
	
	//是展开节点
	boolean expose=true;



	@Override
	public Point paint(Point offset, GC gc) {
		
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = offset.x;
		bounds.y = offset.y;
		bounds.height = 27;
		bounds.width = image_Expose.getBounds().width;
		
		setBounds(bounds);
		
		Point center = new Point(0, 0);
		center.x = bounds.x + bounds.width/2;
		center.y = bounds.y + bounds.height/2;
		
		Point size = new Point(image_Expose.getBounds().width, 
				image_Expose.getBounds().height);
		
		int imagex = center.x - size.x/2;
		int imagey = center.y - size.y/2;
		if (expose) {
			gc.drawImage(image_Expose, imagex, imagey);
		}else {
			gc.drawImage(image_Merge, imagex, imagey);
		}
		
		Point maxSzie = new Point(bounds.width, bounds.height);
		
		return maxSzie;
	}

	public boolean isExpose() {
		return expose;
	}

	public void setExpose(boolean expose) {
		this.expose = expose;
	}
	
}
