import java.lang.Math;

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double grav = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/** 
	* Calculate the distance between two Bodys
	*/
	public double calcDistance(Body B){
		double dx = B.xxPos - this.xxPos;
		double dy = B.yyPos - this.yyPos;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}

	/** 
	* Calculate the force exerted on this body by the given body
	*/
	public double calcForceExertedBy(Body B){
		double distance = calcDistance(B);
		double force = grav * this.mass * B.mass/(distance*distance);
		return force;
	}

	/** 
	* Calculate the force exerted in the X and Y directions
	*/
	public double calcForceExertedByX(Body B){
		double force = calcForceExertedBy(B);
		double distance = calcDistance(B);
		double dx = B.xxPos - this.xxPos;
		double forceByX = force * dx / distance;
		if (forceByX < 0){
			return -forceByX;
		}
		return forceByX;

	}

	public double calcForceExertedByY(Body B){
		double force = calcForceExertedBy(B);
		double distance = calcDistance(B);
		double dy = B.yyPos - this.yyPos;
		double forceByY = force * dy / distance;
		if (forceByY < 0){
			return -forceByY;
		}
		return forceByY;

	}

	/** 
	* Calculate the net X and net Y force exerted by all bodies in that array upon the current Body.
	*/	
	public double calcNetForceExertedByX(Body[] BList){
		double netForceX = 0;
		for (Body b:BList){
			if (!this.equals(b)){
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] BList){
		double netForceY = 0;
		for (Body b:BList){
			if (!this.equals(b)){
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	/** 
	* Calculate how much the forces exerted on the body will cause that body to accelerate
	*/		
	public void update(double dt, double fX, double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel; 
	}

	/** Draw the Body's image at the Body's position*/
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
		StdDraw.show();
	}


}