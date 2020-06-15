public class NBody{

	/** 
	* This must be a static class.
	*/
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName){
		In in = new In(fileName);

		int numPlanets = in.readInt();
		double radius = in.readDouble();

		Body[] BList = new Body[numPlanets];
		for(int i = 0; i < numPlanets; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			BList[i] = new Body(xP, yP, xV, yV, m, img);
		}
		return BList;
	}

	public static void main(String[] args){

		/** Collecting all needed input*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Body[] BList = readBodies(filename);
		int numPlanets = BList.length;


		/** Drawing the background and N bodies*/
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");
		StdDraw.show();

		for (Body body:BList){
			body.draw();
		}

		/** Drawing animations*/
		StdDraw.enableDoubleBuffering();
		double time = 0.0;
		while (time <= T){
			double[] xForces = new double[numPlanets];
			double[] yForces = new double[numPlanets];
			for (int i = 0; i < numPlanets; i++){
				xForces[i] = BList[i].calcNetForceExertedByX(BList);
				yForces[i] = BList[i].calcNetForceExertedByY(BList);
			}
			for (int i = 0; i < numPlanets; i++){
				BList[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0,0,"./images/starfield.jpg");
			for (Body body:BList){
				body.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		/** Print the universe*/
		StdOut.printf("%d\n", BList.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < BList.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  BList[i].xxPos, BList[i].yyPos, BList[i].xxVel,
                  BList[i].yyVel, BList[i].mass, BList[i].imgFileName);
		}

	}



}