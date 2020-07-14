package JoinCustomwidgets.additional;


/**
 * Model to hold the join related data
 * 
 * jion节点 [  源数据表 目标数据表 join类型 ]
  * 相当于一条
 * @author fengyuchao
 * @version 2.0
 */
public class Join {

	public static final int NONE = -1;
	public static final int INNER = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int CROSS = 4;
	public static final int FULL = 5;


}
