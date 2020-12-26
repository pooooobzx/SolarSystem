public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }
    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    public double calcDistance (Planet target)
    {
        return java.lang.Math.sqrt( (target.xxPos - xxPos) * (target.xxPos - xxPos) + (target.yyPos - yyPos) * (target.yyPos - yyPos) );
    }


    public double calcForceExertedBy(Planet b)
    {
        double G = 6.67e-11;
        double distance = calcDistance(b);
        double force = G * mass * b.mass / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - xxPos;
        double force = calcForceExertedBy(b);
        double distance = calcDistance(b);
        double xforce = force * dx / distance;
        return xforce;
    }

    public double calcForceExertedByY(Planet b) {
        double dy = b.yyPos - yyPos;
        double force = calcForceExertedBy(b);
        double distance = calcDistance(b);
        double yforce = force * dy / distance;
        return yforce;
    }

    public double calcNetForceExertedByX(Planet[] bs) {
        double xNetforce = 0;
        for (Planet b : bs) {
            if (b.equals(this)) {
            }
            else {
                xNetforce += calcForceExertedByX(b);
            }
        }
        return xNetforce;
    }

    public double calcNetForceExertedByY(Planet[] bs) {
        double yNetforce = 0;
        for (Planet b : bs) {
            if (b.equals(this)) {
            }
            else {
                yNetforce += calcForceExertedByY(b);
            }
        }
        return yNetforce;
    }
    public void update(double dt, double fX, double fY) {
        double accelarationX = fX / mass;
        double accelarationY = fY / mass;
        xxVel += accelarationX * dt;
        yyVel += accelarationY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
    public void draw ()
    {
        String imageToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imageToDraw);
    }
}