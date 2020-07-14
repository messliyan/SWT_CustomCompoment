package JoinCustomwidgets.elements;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.ResourceManager;

import com.vogella.Activator;

import JoinCustomwidgets.panel.JoinCompent;



public class JoinItemElementDelete extends IDrawElement {

	public JoinItemElementDelete(JoinCompent joinCompent) {
		super(joinCompent);
		// TODO Auto-generated constructor stub
	}



	private Image image_Delete=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/Delete.png");
	private Image image_Delete2=ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/join/Delete2.png");

	
	
	@Override
	public Point paint(Point offset, GC gc) {
		
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = offset.x;
		bounds.y = offset.y;
		bounds.height = 27;
		bounds.width = image_Delete.getBounds().width;
		
		setBounds(bounds);
		
		Point center = new Point(0, 0);
		center.x = bounds.x + bounds.width/2;
		center.y = bounds.y + bounds.height/2;
		
		Point size = new Point(image_Delete.getBounds().width, 
				image_Delete.getBounds().height);
		
		int imagex = center.x - size.x/2;
		int imagey = center.y - size.y/2;
		if (MouseOn) {
			gc.drawImage(image_Delete2, imagex, imagey);
		}else {
			gc.drawImage(image_Delete, imagex, imagey);
		}
		
		Point maxSzie = new Point(bounds.width, bounds.height);
		return maxSzie;
	}


}
