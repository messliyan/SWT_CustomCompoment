package JoinCustomwidgets.additional;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 用来提示的窗口
 * 
 * @author Lijiabin
 *
 */
public class PromotShell extends Shell {
	private volatile static PromotShell shell;

	Display display;

	private PromotShell(Display display) {
		// TODO Auto-generated constructor stub
		super(display, SWT.TOOL | SWT.ON_TOP);
	}

	public static PromotShell newInstance(String title, String text, int w, int h) {
		if (shell == null) {
			synchronized (PromotShell.class) {
				if (shell == null) {
					shell = new PromotShell(Display.getDefault());
					java.awt.Point mousePoint = MouseInfo.getPointerInfo().getLocation();
					shell.open(title, text, mousePoint.x, mousePoint.y, w, h);
				}
			}
		}
		return shell;
	}

	private void open(String title, String text, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		FillLayout fl_shell = new FillLayout();
		fl_shell.type = SWT.VERTICAL;
		setLayout(fl_shell);

		Label lblNewLabel = new Label(this, SWT.CENTER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel.setText("(" + title + ")");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblNewLabel_1.setText(text);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int height = (int) screensize.getHeight();

		int NewWidth = (int) screensize.getWidth();
		int NewHeight = (int) screensize.getHeight();

		if (x + w > width) {
			NewWidth = x - w;

		} else {
			NewWidth = x;
		}

		if (y + h > height) {
			NewHeight = y - h;

		} else {
			NewHeight = y;
		}

		setLocation(NewWidth + 10, NewHeight + 10);
		setSize(w, h);
		super.open();
	}

	@Override
	protected void checkSubclass() {
		// TODO 自动生成的方法存根
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shell = null;
		super.dispose();
	}
}
