package JoinCustomwidgets.elements;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

import JoinCustomwidgets.panel.JoinCompent;


public class JoinItemElemenEqual extends IDrawElement {

	public JoinItemElemenEqual(JoinCompent joinCompent) {
		super(joinCompent);
		// TODO Auto-generated constructor stub
		text= "=";
	}


	
	@Override
	public Point paint(Point offset, GC gc) {
		color_fk = SWTResourceManager.getColor(034, 157, 32);
		gc.setForeground(color_fk);
		
		Font font = gc.getFont();
		FontData fontData = font.getFontData()[0];
		fontData.setHeight((int)(fontData.getHeight()));
		fontData.setStyle(SWT.NORMAL);
		
		Font newFont = new Font(null, fontData);
		gc.setFont(newFont);
		
		Point size = gc.stringExtent(text);
		
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = offset.x;
		bounds.y = offset.y;
		bounds.height = 27;
		if(combo != null && !combo.isDisposed()) {
			if(size.x< comboMinWidth) {
				bounds.width = comboMinWidth;
			}else {
				bounds.width = size.x;
			}
		}else {
			bounds.width = size.x;
		}
		
		Point center = new Point(0, 0);
		center.x = bounds.x + bounds.width/2;
		center.y = bounds.y + bounds.height/2;
		
		int textX = center.x - size.x/2;
		int textY = center.y - size.y/2;
		
		gc.drawString(text, textX, textY, true);
		if (MouseOn) {
			gc.setLineStyle(SWT.LINE_SOLID);
			gc.drawLine(textX,center.y+size.y/2-2,bounds.x + bounds.width,center.y+size.y/2-2);
			
		}
		if(text.trim().isEmpty() && (combo == null || combo.isDisposed())) {
			Color beforBK = gc.getBackground();
			Color beforFK = gc.getForeground();
			
			gc.setBackground(color_Empty_bk);
			gc.setForeground(color_Empty_fk);
			
			gc.fillRectangle(bounds);
			gc.drawRectangle(bounds);
			
			gc.setBackground(beforBK);
			gc.setForeground(beforFK);
		}
		
		newFont.dispose();
		gc.setFont(font);
		
		setBounds(bounds);
		
		Point rtSize = new Point(0, 0);
		rtSize.x = bounds.width;
		rtSize.y = bounds.height;
		return rtSize;
	}
	@Override
	protected void intiombo() {
		// TODO Auto-generated method stub
		combo.add(">");
		combo.add(">=");
		combo.add("<");
		combo.add("<=");
		combo.add("=");
		combo.add("!=");
		combo.add("<>");
		combo.add("LIKE");
		combo.add("NOT LIKE");
		combo.add("IN");
		combo.add("NOT IN");
	}
}
