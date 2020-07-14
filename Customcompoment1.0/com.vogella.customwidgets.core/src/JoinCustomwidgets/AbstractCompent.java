package JoinCustomwidgets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

import JoinCustomwidgets.additional.PromotShell;
import JoinCustomwidgets.elements.*;
import JoinCustomwidgets.panel.JoinCompent;

public abstract class AbstractCompent {

	// 元素列表
	protected List<IDrawElement> elements;
	// 组合模式
	protected List<AbstractCompent> list = new ArrayList<AbstractCompent>();

	// 智能提示
	protected PromotShell shell;

	protected JoinCompent joinCompent;

	// 是否画子节点
	protected Boolean paintChild = true;
	// 此元素的监听器不再触发
	protected Boolean canListener = true;

	// 是否绘制Expose元素
	protected Boolean paintExpose = true;

	// 元素
	protected JointemElementAdd jointemElementAdd;
	protected JoinItemElementDelete joinItemElementDelete;
	protected JoinItemElementTableText JoinItemElementTableText1;
	protected JoinItemElementTableText JoinItemElementTableText2;
	protected JoinItemElementJoin JoinItemElementJoin;
	protected JoinItemElemenAnd JoinItemElementAnd;
	protected JoinItemElementColumnText joinItemElementColumnText1;
	protected JoinItemElementColumnText joinItemElementColumnText2;
	protected JoinItemElemenEqual joinItemElemenEqual;
	protected JoinItemElemenDown joinItemElemenDown;
	protected JoinItemElemetTreeExpose joinItemElemetTreeExpose;

	// 每个元素在JoinCompent 都有监听器 在这些组件移除的时候 对应的监听器也需要移除
	protected SelectionListener selectionListener;
	protected MouseMoveListener mouseMoveListener;
	protected AbstractCompent parent;

	public AbstractCompent(JoinCompent joinCompent) {
		// TODO Auto-generated constructor stub
		this.joinCompent = joinCompent;
		elements = new ArrayList<IDrawElement>();
		ADDListener();
		initElements();
		intiElements();
	}

	/**
	 * 初始化 元素
	 * 
	 */
	private void initElements() {
		jointemElementAdd = new JointemElementAdd(joinCompent);
		joinItemElementDelete = new JoinItemElementDelete(joinCompent);
		JoinItemElementTableText1 = new JoinItemElementTableText(joinCompent);
		JoinItemElementTableText2 = new JoinItemElementTableText(joinCompent);
		JoinItemElementJoin = new JoinItemElementJoin(joinCompent);
		JoinItemElementAnd = new JoinItemElemenAnd(joinCompent);
		joinItemElementColumnText1 = new JoinItemElementColumnText(joinCompent);
		joinItemElementColumnText2 = new JoinItemElementColumnText(joinCompent);
		joinItemElemenEqual = new JoinItemElemenEqual(joinCompent);
		joinItemElemenDown = new JoinItemElemenDown(joinCompent);
		joinItemElemetTreeExpose = new JoinItemElemetTreeExpose(joinCompent);

	}

	/**
	 * 初始化 元素数组 钩子方法
	 */
	protected abstract void intiElements();

	/**
	 * 绘制
	 * 
	 * @param offset 绘制元素的起始点
	 * @param gc     画笔
	 * @return
	 */
	public Point paint(Point offset, GC gc) {
		int gap = 5;// 组件元素之间的间隔
		int tab = 23;// 子节点的缩进
		int height = 23;// 本元素的高度
		Point eleOffset = new Point(0, 0);
		eleOffset.x += offset.x;
		eleOffset.y += offset.y;
		// 绘制本组件的元素

		if (ListChildCompent().isEmpty()) {
			paintExpose = false;
		} else {
			paintExpose = true;
		}

		for (IDrawElement ele : getElements()) {
			if (ele instanceof JoinItemElemetTreeExpose && !paintExpose) {
				continue;
			}
			eleOffset.x += gap;
			Point eleSize = ele.paint(eleOffset, gc);
			eleOffset.x += eleSize.x;
		}

		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = offset.x;
		bounds.y = offset.y;
		bounds.width = eleOffset.x + gap;
		bounds.height = height;

		Point subOffset = new Point(offset.x, offset.y);
		subOffset.x += tab;
		subOffset.y += bounds.height;

		int maxWidth = bounds.width;
		int maxHeigth = bounds.height;

		// 设置虚线风格
		gc.setLineStyle(SWT.LINE_DOT);
		// 设置虚线间隔 虚线的定义：“线+缺口+线+缺口+线+缺口…
		gc.setLineDash(new int[] { 2, 2 });
		if (paintChild) {
			for (AbstractCompent subItem : ListChildCompent()) {
				// 绘制父组件和子组件的连线
				// 线的颜色
				gc.setForeground(SWTResourceManager.getColor(42, 26, 71));
				// 竖线
				gc.drawLine(offset.x + tab / 2, offset.y + height, offset.x + tab / 2, subOffset.y + height / 2);
				// 横线
				gc.drawLine(offset.x + tab / 2, subOffset.y + height / 2, offset.x + tab, subOffset.y + height / 2);
				// 绘制子组件
				Point subSize = subItem.paint(subOffset, gc);
				maxWidth = subSize.x > maxWidth ? subSize.x : maxWidth;
				subOffset.y += subSize.y;
				maxHeigth += subSize.y;
			}
		}

		Point rtSize = new Point(maxWidth, maxHeigth);

		return rtSize;
	}

	/**
	 * 添加监听
	 */
	protected void ADDListener() {
		selectionListener = new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (canListener) {

					Point point = new Point(e.x, e.y);
					if (jointemElementAdd.belong(point)) {
						addWidgetSelect();
					}
					if (joinItemElementDelete.belong(point)) {
						delectWidgetSelect();
					}
					if (joinItemElemetTreeExpose.belong(point)) {
						TreeExposeWidgetSelect();
					}
					if (joinItemElemenDown.belong(point)) {
						DownWidgetSelect();
					}
					if (JoinItemElementJoin.belong(point)) {
						JoinItemElementJoin.mouseDownWithCombo();
					}
					if (JoinItemElementAnd.belong(point)) {
						JoinItemElementAnd.mouseDownWithCombo();
					}
					if (joinItemElemenEqual.belong(point)) {
						joinItemElemenEqual.mouseDownWithCombo();
					}

				}
				// 检查树形结构
				joinCompent.JudgmentStructure(joinCompent.getAllroot());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		joinCompent.addSelectionListener(selectionListener);

		mouseMoveListener = new MouseMoveListener() {

			@Override
			public void mouseMove(MouseEvent e) {
				// TODO Auto-generated method stub
				if (canListener) {
					Point point = new Point(e.x, e.y);

					if (Belog(jointemElementAdd, point)) {
						return;
					}
					if (Belog(joinItemElementDelete, point)) {
						return;
					}
					if (Belog(JoinItemElementTableText1, point)) {
						return;
					}
					if (Belog(JoinItemElementTableText2, point)) {
						return;
					}
					if (Belog(JoinItemElementJoin, point)) {
						return;
					}
					if (Belog(JoinItemElementAnd, point)) {
						return;
					}
					if (Belog(joinItemElementColumnText1, point)) {
						return;
					}
					if (Belog(joinItemElementColumnText2, point)) {
						return;
					}
					if (Belog(joinItemElemenEqual, point)) {
						return;
					}
					if (Belog(joinItemElemenDown, point)) {
						return;
					}
				}
			}
		};
		joinCompent.addMouseMoveListener(mouseMoveListener);
	}

	/**
	 * 鼠标悬浮在组件上了
	 * 
	 * @param iDrawElement
	 */
	private Boolean Belog(IDrawElement iDrawElement, Point point) {
		if (iDrawElement.belong(point)) {
			// 智能提示不要影响别人

			if (!iDrawElement.isMouseOn()) {
				if (iDrawElement instanceof JointemElementAdd) {
					addPromotShellCreate();
				} else if (iDrawElement instanceof JoinItemElementDelete) {
					delectPromotShellCreate();
				} else if (iDrawElement instanceof JoinItemElemenDown) {
					DownPromotShellCreate();
				}

				iDrawElement.setMouseOn(true);
				joinCompent.redraw();
				return true;
			}
		} else {
			if (iDrawElement.isMouseOn()) {
				iDrawElement.setMouseOn(false);
				if (shell != null) {
					shell.dispose();
				}
				joinCompent.redraw();
				return true;
			}

		}
		return false;
	}

	/**
	 * 触发了add按钮的点击事件
	 */
	protected void addWidgetSelect() {
		// TODO Auto-generated method stub
		System.out.println("点击了添加");
		Condition condition = new Condition(joinCompent);
		add(condition);
		// 如果节点没有展开则子元素及子节点全部展开

		TreeALLExposeWidget();

	}

	/**
	 * 触发了删除按钮的点击事件
	 */
	protected void delectWidgetSelect() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		System.out.println("点击了删除");
		parent.remove(this);
		joinCompent.removeMouseMoveListener(mouseMoveListener);
		if (shell != null) {
			shell.dispose();
		}

		delectAllChild(this);
		joinCompent.redraw();

	}

	private void delectAllChild(AbstractCompent adb) {
		List<AbstractCompent> list = adb.ListChildCompent();
		for (int i = 0; i < list.size(); i++) {
			adb.remove(list.get(i));
			i--; // 因为位置发生改变，所以必须修改i的位置
		}
	}

	/**
	 * 触发了下钻事件
	 */
	protected void DownWidgetSelect() {
		// TODO Auto-generated method stub
		System.out.println("点击了下钻");

	}

	/**
	 * 触发了tree按钮的展开事件
	 */
	protected void TreeExposeWidgetSelect() {
		// TODO Auto-generated method stub
		System.out.println("点击了展开");
		if (joinItemElemetTreeExpose.isExpose()) {
			joinItemElemetTreeExpose.setExpose(false);
			paintChild = false;
			FailChildListener(false);
		} else {
			joinItemElemetTreeExpose.setExpose(true);
			paintChild = true;
			FailChildListener(true);
		}

	}

	/**
	 * 子元素全部展开事件
	 */
	protected void TreeALLExposeWidget() {
		// TODO Auto-generated method stub
		joinItemElemetTreeExpose.setExpose(true);
		paintChild = true;
		FailChildListener(true);
		for (AbstractCompent adAbstractCompent : list) {
			adAbstractCompent.TreeALLExposeWidget();
		}

	}

	/**
	 * 创建add按钮的悬浮提示内容和大小
	 */
	protected void addPromotShellCreate() {
		// TODO Auto-generated method stub
		shell = PromotShell.newInstance("添加Join", "这个工具用来添加新的Join条件", 250, 50);
	}

	/**
	 * 创建Delect按钮的悬浮提示内容和大小
	 */
	protected void delectPromotShellCreate() {
		// TODO Auto-generated method stub
		shell = PromotShell.newInstance("删除Join", "这个工具用来删除此Join条件", 250, 50);
	}

	protected void DownPromotShellCreate() {
		// TODO Auto-generated method stub
		shell = PromotShell.newInstance("条件下钻", "这个工具用来把此条件变为复合条件", 250, 50);
	}

	/**
	 * 组合模式 用来创建树形结构
	 * 
	 * @param af
	 */
	public void add(AbstractCompent af) {
		// TODO Auto-generated method stub
		list.add(af);
		af.setParentCompent(this);
		joinCompent.addSelectionListener(af.getSelectionListener());
		joinCompent.addMouseMoveListener(af.getMouseMoveListener());

	}

	/**
	 * 指定位置进行添加
	 * 
	 * @param af
	 */
	public void add(int i, AbstractCompent af) {
		// TODO Auto-generated method stub
		list.add(i, af);
		af.setParentCompent(this);
		joinCompent.addSelectionListener(af.getSelectionListener());
		joinCompent.addMouseMoveListener(af.getMouseMoveListener());

	}

	/**
	 * 得到指定子元素的位置
	 * 
	 * @param af
	 */
	public int getIndex(AbstractCompent af) {
		// TODO Auto-generated method stub
		return list.indexOf(af);
	}

	/**
	 * 删除子元素
	 * 
	 * @param af
	 */
	public void remove(AbstractCompent af) {
		// TODO Auto-generated method stub
		joinCompent.removeSelectionListener(af.getSelectionListener());
		joinCompent.removeMouseMoveListener(af.getMouseMoveListener());
		if (list.remove(af)) {
			// 子节点的选择监听器也一起删除
			for (int i = 0; i < af.ListChildCompent().size(); i++) {
				AbstractCompent childAf = af.ListChildCompent().get(i);
				joinCompent.removeSelectionListener(childAf.getSelectionListener());
				joinCompent.removeMouseMoveListener(childAf.getMouseMoveListener());
				af.remove(childAf);
				i--; // 因为位置发生改变，所以必须修改i的位置
			}

			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

	/**
	 * （用于元素换位置）添加子元素 不删除选择监听器 未来必须把元素出息加上去
	 * 
	 * @param af
	 */
	public void addNotListener(AbstractCompent af) {
		// TODO Auto-generated method stub
		list.add(af);
		af.setParentCompent(this);
	}

	/**
	 * （用于元素换位置） 条件添加多个子节点
	 * 
	 * @param af
	 */
	public void addList(List<AbstractCompent> af) {
		// TODO Auto-generated method stub
		for (AbstractCompent abstractCompent : af) {
//			//克隆一份 避免监听器冲突
			abstractCompent.addList(abstractCompent.ListChildCompent());
			addNotListener(abstractCompent);
		}

	}

	/**
	 * （用于元素换位置）删除子元素 不删除选择监听器 未来必须把元素出息加上去
	 * 
	 * @param af
	 */
	public void removeNotListener(AbstractCompent af) {
		// TODO Auto-generated method stub
		if (list.remove(af)) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

	/**
	 * 得到某个子元素
	 * 
	 * @param i
	 * @return
	 */
	public AbstractCompent get(int i) {
		// TODO Auto-generated method stub
		return list.get(i);
	}

	/**
	 * 得到所有的子元素
	 * 
	 * @return
	 */
	public List<AbstractCompent> ListChildCompent() {
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * 得到上层子元素
	 * 
	 * @return
	 */
	public AbstractCompent getParentCompent() {
		// TODO Auto-generated method stub
		return parent;

	}

	public void setParentCompent(AbstractCompent abstractCompent) {
		// TODO Auto-generated method stub

		parent = abstractCompent;
	}

	public SelectionListener getSelectionListener() {
		return selectionListener;
	}

	public MouseMoveListener getMouseMoveListener() {
		return mouseMoveListener;
	}

	// 用来绘制组件的方法
	private List<IDrawElement> getElements() {
		// TODO Auto-generated method stub
		return elements;
	}

	// 设置所有的子元素监听
	public void FailChildListener(Boolean flag) {
		// TODO Auto-generated method stub
		for (AbstractCompent adAbstractCompent : list) {
			adAbstractCompent.setCanListener(flag);
			adAbstractCompent.FailChildListener(flag);
		}

	}

	public Boolean getCanListener() {
		return canListener;
	}

	public void setCanListener(Boolean canListener) {
		this.canListener = canListener;
	}
	
	public String getSql() {
		String Sqltext="";
		for (IDrawElement iDrawElement : elements) {
			if (!"".equals(iDrawElement.getText())) {
				Sqltext+=iDrawElement.getText()+" ";
			}
			
		}
		return Sqltext;
	}
}
