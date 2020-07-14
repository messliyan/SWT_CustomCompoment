package org.eclipse.swt.examples.customcompoment.fiowlayout.views;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import com.vogella.customwidgets.core.checkbox.Checkbox;
import javax.inject.Inject;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class FlowCompomentView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.swt.examples.customcompoment.fiowlayout.views.SampleView";

	@Inject IWorkbench workbench;
	
	 
	

	@Override
	public void createPartControl(Composite parent) {
		

		GridLayoutFactory.fillDefaults().applyTo(parent);
		Checkbox checkbox = new Checkbox(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(checkbox);
		checkbox.setSelection(true);
		checkbox.setText("Custom SWT check button");

		checkbox.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		checkbox.setCenterColor(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		checkbox.setCheckColor(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
	
		Button checkButton = new Button(parent, SWT.CHECK);
		checkButton.setSelection(true);
		checkButton.setText("Usual SWT check button");
	
		
	}

	


	
	
	@Override
	public void setFocus() {
		
	}
}
