package mygeom;

public class Point2 extends Tuple2 {

		public Point2() {
			super();
		}
	
		public Point2(Point2 a) {
			super(a);
		}
		
		public Point2(Point2 a,Point2 b) {
			super(b);
			this.sub(a);
		}
		
		public Point2(double x,double y) {
			super(x,y);
		}
				
}
