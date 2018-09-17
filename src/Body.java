public class Body {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		double rad = (this.getX()-b.getX())*(this.getX()-b.getX()) + (this.getY()-b.getY())*(this.getY()-b.getY());
		return Math.sqrt(rad);
	}
	
	public double calcForceExertedBy(Body b) {
		return ((6.67e-11)*((this.getMass()*b.getMass())/Math.pow(this.calcDistance(b), 2)));
	}
	
	public double calcForceExertedByX(Body b) {
		return this.calcForceExertedBy(b)*((b.getX()-this.getX())/this.calcDistance(b));
	}
	
	public double calcForceExertedByY(Body b) {
		return this.calcForceExertedBy(b)*((b.getY()-this.getY())/this.calcDistance(b));
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double netf = 0;
		for(Body b: bodies) {
			if (! b.equals(this)) {
				netf += this.calcForceExertedByX(b);
			}
		}
		return netf;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double netf = 0;
		for(Body b: bodies) {
			if (! b.equals(this)) {
				netf += this.calcForceExertedByY(b);
			}
		}
		return netf;
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}