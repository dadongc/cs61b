public class Planet {
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Planet p) {
    return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
  }

  public double calcForceExertedBy(Planet p) {
    double G = 6.67E-11;
    double distance = this.calcDistance(p);
    return this.mass * p.mass * G / distance / distance;
  }

  public double calcForceExertedByX(Planet p) {
    if (equals(p)) {
      return 0;
    }
    double force = calcForceExertedBy(p);
    double forceX = force * Math.abs(this.xxPos - p.xxPos) / calcDistance(p);
    return forceX;
  }

  public double calcForceExertedByY(Planet p) {
    if (equals(p)) {
      return 0;
    }
    double force = calcForceExertedBy(p);
    double forceY = force * Math.abs(this.yyPos - p.yyPos) / calcDistance(p);
    return forceY;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double netForceX = 0;
    for (Planet p: planets) {
      if (this.equals(p)) {
        continue;
      }
      double force = calcForceExertedBy(p);
      netForceX += force * (this.xxPos - p.xxPos) / calcDistance(p);
    }
    return -netForceX;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double netForceY = 0;
    for (Planet p: planets) {
      if (this.equals(p)) {
        continue;
      }
      double force = calcForceExertedBy(p);
      netForceY += force * (this.yyPos - p.yyPos) / calcDistance(p);
    }
    return -netForceY;
  }

  public boolean equals(Planet p) {
    return xxPos == p.xxPos 
    && yyPos == p.yyPos 
    && xxVel == p.xxVel 
    && yyVel == p.yyVel 
    && mass == p.mass
    && imgFileName == p.imgFileName;
  }

  public void update(double deltaT, double forceX, double forceY) {
    xxVel += forceX / mass * deltaT;
    yyVel += forceY / mass * deltaT;
    xxPos += xxVel * deltaT;
    yyPos += yyVel * deltaT;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, imgFileName);
  }
}


