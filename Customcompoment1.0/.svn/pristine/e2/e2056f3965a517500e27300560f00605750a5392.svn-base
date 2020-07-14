package JoinCustomwidgets.panel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import JoinCustomwidgets.AbstractCompent;
import JoinCustomwidgets.Condition;
import JoinCustomwidgets.Conjunction;
import JoinCustomwidgets.Root;
import JoinCustomwidgets.TableRelation;
import JoinCustomwidgets.additional.PromotShell;
import JoinCustomwidgets.elements.IDrawElement;

public class JoinCompent extends Canvas {

	private ListenerList selectionListener = new ListenerList();
	private ListenerList MouseMoveListener = new ListenerList();
	private List<AbstractCompent> compents;
	private Combo combo;
	private Point textExtent;

	public JoinCompent(Composite parent, int style) {
		super(parent, style);
		addListeners();
	}

	private void addListeners() {
		// 监听器太烦了 如果可以每次重绘的时候根据compents 重新生成监听器就好了但是MouseListener很难控制 因为找不见它的集合
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				GC gc = e.gc;
				gc.setAdvanced(true);
				e.gc.setAntialias(SWT.ON);

				int gap = 1;
				int tab = 0;

				Point offset = new Point(tab, gap);

				int width = offset.x;
				for (AbstractCompent subItem : compents) {
					Point subSize = subItem.paint(offset, gc);
					offset.y += gap;
					offset.y += subSize.y;
					width = subSize.x > width ? subSize.x : width;
				}

			}
		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent e) {

				// 避免出现 提示框不消失的问题
//				 PromotShell  shell=PromotShell.newInstance("添加Join","这个工具用来添加新的Join条件",250,50);
//				 if (shell!=null) {
//						shell.dispose();
//				 }

				notifySelectionListener(e);

				redraw();
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				closeCombo();
			}

		});

	}

	protected void notifySelectionListener(MouseEvent e) {
		checkWidget();
		Object[] listeners = selectionListener.getListeners();
		for (Object listener : listeners) {
			if (listener instanceof SelectionListener) {

				Event event = new Event();
				event.widget = e.widget;
				SelectionEvent selectionEvent = new SelectionEvent(event);
				selectionEvent.data = e.data;
				selectionEvent.display = e.display;
				selectionEvent.stateMask = e.stateMask;
				selectionEvent.x = e.x;
				selectionEvent.y = e.y;

				((SelectionListener) listener).widgetSelected(selectionEvent);
			}
		}

		// 每次 点击界面之后 重新设定监听器。避免出现删除不干净的情况
		resetListener();
	}

	@Override
	public void removeMouseMoveListener(MouseMoveListener listener) {
		// TODO Auto-generated method stub
		super.removeMouseMoveListener(listener);
		MouseMoveListener.remove(listener);

	}

	@Override
	public void addMouseMoveListener(MouseMoveListener listener) {
		// TODO Auto-generated method stub
		super.addMouseMoveListener(listener);
		MouseMoveListener.add(listener);
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		checkWidget();
		int textWidth = textExtent != null ? textExtent.x : 0;
		return super.computeSize(Math.max(26 + textWidth, wHint), Math.max(26, hHint), changed);
	}

	/**
	 * 重设监听器
	 */
	private void resetListener() {
		for (Object object : MouseMoveListener) {
			removeMouseMoveListener((MouseMoveListener) object);
		}
		for (Object object : selectionListener) {
			removeSelectionListener((SelectionListener) object);
		}

		addAllListener(compents);
	}

	/**
	 * 递归遍历组件 增加监听器
	 * 
	 * @param compents2
	 */
	private void addAllListener(List<AbstractCompent> compents2) {
		for (AbstractCompent abstractCompent : compents2) {
			addSelectionListener(abstractCompent.getSelectionListener());
			addMouseMoveListener(abstractCompent.getMouseMoveListener());
			addAllListener(abstractCompent.ListChildCompent());
		}

	}

	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		if (null == listener) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		selectionListener.add(listener);
	}

	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		if (null == listener) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		selectionListener.remove(listener);
	}

	public void setAbstractCompent(List<AbstractCompent> compents) {
		checkWidget();
		this.compents = compents;
	}

	public List<AbstractCompent> getAbstractCompent() {
		checkWidget();
		return compents;
	}

	/**
	 * 递归 判断 list是否符合结构 如果list下面有俩个以上条件且 且不是and 则 加一个and的元素为父元素 如果list 下有条件也有and元素
	 * 且不是and 条件加到 and下面 如果 list下面没有条件 则 且此节点为 and 咋去掉
	 * 
	 * @param Targetcompents 需要的遍历元素
	 */
	public Boolean JudgmentStructure(AbstractCompent Targetcompents) {

		Boolean flag = false;
		List<AbstractCompent> conditionlist = new ArrayList<AbstractCompent>();
		// 解决ConcurrentModificationException异常
		for (int i = 0; i < Targetcompents.ListChildCompent().size(); i++) {
			AbstractCompent abstractCom = Targetcompents.ListChildCompent().get(i);

			if (abstractCom instanceof Condition) {
				conditionlist.add(abstractCom);
			}
			Boolean remove = JudgmentStructure(abstractCom);
			if (remove) {
				i--; // 因为位置发生改变，所以必须修改i的位置
			}
		}

		// 先添加再移除 否则监听器也没有了
		if (conditionlist.size() > 1 && !(Targetcompents instanceof Conjunction)) {
			Conjunction conjunction = new Conjunction(this);
			conjunction.addList(conditionlist);
			for (AbstractCompent abstractCompent : conditionlist) {
				Targetcompents.removeNotListener(abstractCompent);
			}
			Targetcompents.add(conjunction);
		}
		if (conditionlist.size() == 1 && !(Targetcompents instanceof Conjunction)) {
			for (AbstractCompent abstractCompent2 : Targetcompents.ListChildCompent()) {
				if (abstractCompent2 instanceof Conjunction) {
					if ((abstractCompent2.ListChildCompent().indexOf(conditionlist.get(0))) == -1) {
						abstractCompent2.addList(conditionlist);
						for (AbstractCompent abstractCompent : conditionlist) {
							Targetcompents.removeNotListener(abstractCompent);
						}
						break;
					}
				}
			}

		}

		if (conditionlist.size() == 0 && Targetcompents instanceof Conjunction) {
			if (Targetcompents.ListChildCompent().isEmpty()) {
				Targetcompents.getParentCompent().remove(Targetcompents);
				flag = true;
			}

		}
		return flag;
	}

	/**
	 * 得到根元素
	 * 
	 * @return
	 */
	public AbstractCompent getAllroot() {
		return compents.get(0);
	}

	public Combo createCombo() {
		closeCombo();
		combo = new Combo(this, SWT.READ_ONLY);
		combo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				combo.dispose();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		return combo;
	}

	public void closeCombo() {
		if (combo != null && !combo.isDisposed()) {
			combo.dispose();
			combo = null;
		}
	}

	public String getAllSql() {
		JudgmentStructure(getAllroot());
		String Sqltext = makSql(getAllroot());
		return Sqltext;
	}

	/**
	 * 得到本节点的sql语句
	 *   递归 生成sql语句 
	 * 情况1；如果是表关系 将sql放在 子sql的最前面
	 *  情况2：父为连接 自己为连接 则前后加一个（） 
	 *  情况3：多条件
	 * 使用上层的连接符连接 
	 * 情况4：父不是连接符 直接输出
	 * @param Targetcompents
	 * @return
	 */
	private String makSql(AbstractCompent Targetcompents) {

		if (Targetcompents instanceof Condition) {
			return Targetcompents.getSql();
		}		
		String newsql = "";
		if (Targetcompents instanceof Conjunction) {
			
			for (int i = 0; i < Targetcompents.ListChildCompent().size(); i++) {
				AbstractCompent abstractCompent = Targetcompents.get(i);
				if (i!=0) {
					newsql+="\n"+Targetcompents.getSql()+"  ";
				}
				if (abstractCompent instanceof Conjunction) {
					newsql+="("+makSql(abstractCompent)+")";
				}
				if (abstractCompent instanceof Condition) {
					newsql+=makSql(abstractCompent);
				}	
			}	
		}
		if (Targetcompents instanceof Root) {
			for (AbstractCompent abstractCompent : Targetcompents.ListChildCompent()) {
				newsql +=makSql(abstractCompent);
			}
		}
		if (Targetcompents instanceof TableRelation) {
			newsql += "\n  " + Targetcompents.getSql() + "on \n " ;
			for (AbstractCompent abstractCompent : Targetcompents.ListChildCompent()) {
				newsql +=makSql(abstractCompent);
			}
		}
		
		return newsql;
	}
	
}
