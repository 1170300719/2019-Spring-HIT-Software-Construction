package circularOrbit;

import java.util.List;
import java.util.Set;
import difference.Difference;
import exception.objectNoFoundException;
import track.Track;

public interface CircularOrbit<L, E> {
	// Abstraction function:
	// CircularOrbit<L, E>是一个由多个Track，多个轨道物体和中心物体组成的对轨道结构的抽象
	// 所以AF是CircularOrbit<L, E>到真实的轨道结构的映射

	// Representation invariant:
	// 轨道不能重名，不能有轨道具有相同半径

	// Safety from rep exposure:
	// 设置关键数据为protected final
	// 在有必要的时候使用防御性拷贝
	/**
	 * 设置中心物体
	 * 
	 * @param centralObject
	 */
	public void setCentralObject(L centralObject);

	/**
	 * 返回中心物体
	 * 
	 * @return 中心物体
	 */
	public L getCentralObject();

	/**
	 * 增加一条轨道
	 * 
	 * @param t
	 * @return 成功返回true 否则false
	 */
	public boolean addTrack(Track t);

	/**
	 * 删除一条轨道
	 * 
	 * @param t
	 * @return 成功返回true 否则false
	 */
	public boolean removeTrack(Track t);

	/**
	 * 返回轨道数目
	 * 
	 * @return 轨道数目
	 */
	public Integer getTrackNum();

	/**
	 * 统计一条轨道有多少物体
	 * 
	 * @param t 轨道
	 * @return 轨道t上的物体数
	 */
	public Integer getObjectNumonTrack(Track t);

	/**
	 * 返回一条轨道的物体
	 * 
	 * @param t
	 * @return
	 */
	public List<E> getObjectonTrack(Track t);

	/**
	 * 向轨道上增加物体
	 * 
	 * @param t      目标轨道
	 * @param object 增加的物体
	 * @return 成功返回true 否则false
	 */
	public boolean addObjectToTrack(Track t, E object);

	/**
	 * 删去一个轨道上的某个物体
	 * 
	 * @param t
	 * @param object
	 * @return 成功返回true 否则false
	 */
	public boolean removeObjectOnTrack(Track t, E object);

	/**
	 * 在两个轨道物体之间新增关系
	 * 
	 * @param object1
	 * @param object2
	 * @param distance
	 * @return 成功返回true 否则false
	 */
	public boolean addtrackRelation(E object1, E object2, double distance);

	/**
	 * 在轨道物体和中心物体之间新增关系
	 * 
	 * @param object
	 * @param distance
	 * @return 成功返回true 否则false
	 */
	public boolean addcentralRelation(E object, double distance);

	/**
	 * 计算轨道系统的信息熵
	 * 
	 * @return 信息熵
	 */
	public double getObjectDistributionEntropy();

	/**
	 * 获得两物体间逻辑距离
	 * 
	 * @param e1
	 * @param e2
	 * @return 逻辑距离
	 */
	public int getLogicalDistance(E e1, E e2);

	/**
	 * 比较当前orbit和目标orbit的不同
	 * 
	 * @param c 目标orbit
	 * @return 一个different对象，记录两个orbit的区别
	 */
	public Difference getDifference(CircularOrbit<L, E> c);

	/**
	 * 获得当前orbit包含的所有轨道按半径排列成的链表
	 * 
	 * @return 半径有序的Track链表
	 */
	public List<Track> getSortedTracks();

	/**
	 * 可视化方法
	 */
	public void drawpicture();

	/**
	 * 判断当前Orbit是否包含某个元素e
	 * 
	 * @param e
	 * @return 成功返回true 否则false
	 */
	public boolean contains(E e);

	/**
	 * 返回某个元素e所在的Object对象
	 * 
	 * @param ob3
	 * @return 轨道
	 * @throws objectNoFoundException 
	 */
	public Track getObjectTrack(E e) throws objectNoFoundException;

	/**
	 * 返回某个元素e所在的Object对象
	 * 
	 * @param name 元素名
	 * @return 轨道
	 * @throws objectNoFoundException 
	 */
	public Track getObjectTrack(String name) throws objectNoFoundException;

	/**
	 * 返回与中心连接的物体
	 * 
	 * @return 物体构成的集合
	 */
	public Set<E> getCentralConnectedObject();

	/**
	 * 返回与某个轨道物体连接的所有物体
	 * 
	 * @return 物体构成的集合
	 */
	public Set<E> getTrackConnectedObject(E object);
}
