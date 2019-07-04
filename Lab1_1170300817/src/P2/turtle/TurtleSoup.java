/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle     the turtle context
	 * @param sideLength length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		for (int i = 0; i < 4; i++) {
			turtle.forward(sideLength);
			turtle.turn(90);
		}
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		return (sides - 2) * (180.0 / sides);
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		return (int) Math.round((360.0 / (180 - angle)));
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle     the turtle context
	 * @param sides      number of sides of the polygon to draw
	 * @param sideLength length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		double angle = 180 - calculateRegularPolygonAngle(sides);
		for (int i = 0; i < sides; i++) {
			turtle.forward(sideLength);
			turtle.turn(angle);
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the Bearing towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentBearing. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentBearing current direction as clockwise from north
	 * @param currentX       current location x-coordinate
	 * @param currentY       current location y-coordinate
	 * @param targetX        target point x-coordinate
	 * @param targetY        target point y-coordinate
	 * @return adjustment to Bearing (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		double dgree = Math.toDegrees(Math.atan2(targetY - currentY, targetX - currentX));
		dgree = (90 - dgree) - currentBearing;
		if (dgree < 0)
			dgree += 360;
		return dgree;
	}

	/**
	 * Given a sequence of points, calculate the Bearing adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateBearingToPoint() to implement this function.
	 * 
	 * @param xCoords list of x-coordinates (must be same length as yCoords)
	 * @param yCoords list of y-coordinates (must be same length as xCoords)
	 * @return list of Bearing adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		List<Double> result = new ArrayList<Double>();
		result.add(calculateBearingToPoint(0.0, xCoords.get(0), yCoords.get(0), xCoords.get(1), yCoords.get(1)));
		if (xCoords.size() > 2) {
			for (int i = 2; i < xCoords.size(); i++)
				result.add(calculateBearingToPoint(result.get(i - 2), xCoords.get(i - 1), yCoords.get(i - 1),
						xCoords.get(i), yCoords.get(i)));
		}
		return result;
	}

	/**
	 * Given a set of points, compute the convex hull, the smallest convex set that
	 * contains all the points in a set of input points. The gift-wrapping algorithm
	 * is one simple approach to this problem, and there are other algorithms too.
	 * 
	 * @param points a set of points with xCoords and yCoords. It might be empty,
	 *               contain only 1 point, two points or more.
	 * @return minimal subset of the input points that form the vertices of the
	 *         perimeter of the convex hull
	 */
	public static Set<Point> convexHull(Set<Point> points) {
		ArrayList<Point> convex = new ArrayList<Point>();
		ArrayList<Point> points2 = new ArrayList<Point>();
		points2.addAll(points);
		int n = points2.size();
		if (n < 4) // �㼯�е�С��4ʱ��ֱ�ӷ���
			return points;
		Point firstPoint = points2.get(0);
		for (Point p : points) { // �ҵ������µ�һ������Ϊ��ʼ��
			if (p.x() < firstPoint.x()) {
				firstPoint = p;
			} else if (p.x() == firstPoint.x() && p.y() < firstPoint.y()) {
				firstPoint = p;
			}
		}
		convex.add(firstPoint);//��ʼ����뼯��
		points2.remove(firstPoint);//��ԭʼ������ȥ����ʼ��
		Point prePoint = firstPoint;
		int i = 0;
		do {
			if (i == 1)
				points2.add(firstPoint);//�ٰ�ԭʼ����뼯�ϣ���Ϊѭ������ֹ����
			double targetangle = 360;//ÿ��ѭ���ҵ�����С�Ƕȣ����ʻ�Ϊ360
			Point targetPoint = null;//��ʼ��Ŀ���Ϊ��
			double targetdis = 0;
			for (Point p : points2) {
				double tempangle = calculateBearingToPoint(0, (int) prePoint.x(), (int) prePoint.y(), (int) p.x(),
						(int) p.y());//����ת���
				double tempdis = Math.pow(prePoint.x() - p.x(), 2) + Math.pow(prePoint.y() - p.y(), 2);//�������
				if (tempangle < targetangle) {
					targetangle = tempangle;
					targetPoint = p;
					targetdis = tempdis;//���ת��Ƿ���Ҫ�������µ�targetangle��targetdis
				}
				else if (tempangle == targetangle && tempdis > targetdis) {//ȡԶ�˵�
					targetdis = tempdis;
					targetPoint = p;
				}
			}
			convex.add(targetPoint);
			points2.remove(targetPoint);
			prePoint = targetPoint;
			i++;
		} while (convex.get(i) != firstPoint);
		Set<Point> result = new HashSet<Point>();
		result.addAll(convex);
		return result;
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {

		PenColor[] color = new PenColor[9];
		color[0] = PenColor.MAGENTA;
		color[1] = PenColor.BLUE;
		color[2] = PenColor.RED;
		color[3] = PenColor.PINK;
		color[4] = PenColor.YELLOW;
		color[5] = PenColor.GREEN;
		color[6] = PenColor.CYAN;
		color[7] = PenColor.BLUE;
		color[8] = PenColor.MAGENTA;

		for (int i = 0; i < 100; i++) {
			drawRegularPolygon(turtle, 4, (2 * i + 10));
			turtle.turn(10);
			turtle.color(color[i % 8]);
		}
	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();

		// draw the window
		turtle.draw();
		// drawSquare(turtle, 40);
		drawPersonalArt(turtle);

	}

}
