public class Body {
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
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
		//gets the X position
		return myXPos;
	}
	
	public double getY() {
		//gets the Y position
		return myYPos;
	}
	
	public double getXVel() {
		//gets the XVelocity
		return myXVel;
	}
	
	public double getYVel() {
		//gets the YVelocity
		return myYVel;
	}
	
	public double getMass() {
		//Gets the bodies mass
		return myMass;
	}
	
	public String getName() {
		//Gets the name of the file and makes it public
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		//gets the distance between two bodies
		double rad = (this.getX()-b.getX())*(this.getX()-b.getX()) + (this.getY()-b.getY())*(this.getY()-b.getY());
		return Math.sqrt(rad);
	}
	
	public double calcForceExertedBy(Body b) {
		//gets the force exerted on this body by another body
		return ((6.67e-11)*((this.getMass()*b.getMass())/Math.pow(this.calcDistance(b), 2)));
	}
	
	public double calcForceExertedByX(Body b) {
		//gets the force exerted in the x direction by another body
		return this.calcForceExertedBy(b)*((b.getX()-this.getX())/this.calcDistance(b));
	}
	
	public double calcForceExertedByY(Body b) {
		//gets the force exerted in the y direction by another body
		return this.calcForceExertedBy(b)*((b.getY()-this.getY())/this.calcDistance(b));
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		//gets the total X force exerted on this body by all the other bodies
		double netf = 0;
		for(Body b: bodies) {
			if (! b.equals(this)) {
				netf += this.calcForceExertedByX(b);
			}
		}
		return netf;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		//gets the total y force exerted on this body by all the other bodies
		double netf = 0;
		for(Body b: bodies) {
			if (! b.equals(this)) {
				netf += this.calcForceExertedByY(b);
			}
		}
		return netf;
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		//updates the new locations and velocity of the body as time goes on
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
		//puts the pictures of the bodies into the simulation
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}