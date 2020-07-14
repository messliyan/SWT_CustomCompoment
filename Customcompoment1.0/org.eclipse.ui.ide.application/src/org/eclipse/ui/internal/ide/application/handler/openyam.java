package org.eclipse.ui.internal.ide.application.handler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

public class openyam implements IHandler {

	

	@Override
	public void dispose() {
		// TODO 自动生成的方法存根
	}
	private IWorkbenchWindow fWindow;

	private File queryFile() {
		
		FileDialog dialog= new FileDialog(fWindow.getShell(), SWT.OPEN);
		dialog.setText("Open File"); //$NON-NLS-1$
		String path= dialog.open();
		if (path != null && path.length() > 0)
			return new File(path);
		return null;
	}
	private String getEditorId(File file) {
		
			
		IEditorRegistry editorRegistry= PlatformUI.getWorkbench().getEditorRegistry();
		IEditorDescriptor descriptor= editorRegistry.getDefaultEditor(file.getName());
//		if (descriptor != null)
//			return descriptor.getId();
		return "org.dadacoalition.yedit.editor.YEdit"; //$NON-NLS-1$
	}

//	private IEditorInput createEditorInput(File file) {
//		IFile iFile=new 
//		FileEditorInput fileEditorInput= new FileEditorInput(file);
//		IPath location= new Path(file.getAbsolutePath());
//		PathEditorInput input= new PathEditorInput(location);
//		return input;
//	}
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO 自动生成的方法存根
		try {
			             //创建工程
			             IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("TestProject");
			             if (!project.exists())
			                project.create(null);
			            if (!project.isOpen())
			                 project.open(null);
			             
			             //创建文件
			             IFile java_file = project.getFile(new Path("/java_file.yml"));
			             InputStream inputStreamJava = new ByteArrayInputStream("key : value".getBytes());
			             if (!java_file.exists())
			                 java_file.create(inputStreamJava, false, null);
			         } catch (CoreException e) {
			             IStatus status = new Status(IStatus.ERROR, "myplugin", 101, "创建资源失败", e);
			           
			        }
		 try {
			             IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			             IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("TestProject");
			             
			             IFile java_file = project.getFile(new Path("/java_file.yml"));
			             IDE.openEditor(page, java_file);            
			         } catch (CoreException e) {
			             IStatus status = new Status(IStatus.ERROR, "myplugin", 102, "打开工作区内文件出错", e);
			          
			          }
		
////		https://blog.csdn.net/soszou/article/details/11152031	
//			//开发区间外的编辑器不能指定编辑器打开 使用默认编辑器
//			File file2 = new File("C:\\Users\\PCPC\\Desktop\\新建文本文档 (2).yml");
//			IFileStore fileStore = EFS.getLocalFileSystem().getStore(new org.eclipse.core.runtime.Path(file2.getAbsolutePath()));
//			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//			try {
//			IDE.openEditorOnFileStore(page, fileStore);
//		
//			} catch (PartInitException e) {
//			
//		}
////	
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO 自动生成的方法存根
		
	}


}
