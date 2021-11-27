public class NBody {
  public static double readRadius(String s) {
    In in = new In(s);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
    return secondItemInFile;
  }

  public static Planet[] readPlanets(String s) {
    In in = new In(s);
    int total = in.readInt();
    double radius = in.readDouble();
    Planet[] planets = new Planet[total];
    for(int i = 0;i < total;i++) {
      planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
    }
    return planets;
  }

  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("args should have at least 3");
      return;
    }
    double T = Double.parseDouble(args[0]);
    double deltaT = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);
    double radius = readRadius(filename);
    String backgroundImg = "images/starfield.jpg";
    StdDraw.enableDoubleBuffering();

    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0, 0, backgroundImg, 2*radius, 2*radius);
    for(Planet p: planets) {
      StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
    }
    StdDraw.show();

    double currentTime = 0.0;
    while(currentTime < T) {
      currentTime += deltaT;
			StdDraw.clear();
      StdDraw.picture(0, 0, backgroundImg, 2*radius, 2*radius);
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];

      for(int i = 0;i < planets.length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0;i < planets.length; i++) {
        Planet p = planets[i];
        p.update(deltaT, xForces[i], yForces[i]);
        StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
      }
      StdDraw.show();
      StdDraw.pause(1);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
    }
    
  }
}
