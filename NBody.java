public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] allplanets = new Planet[num];
        for (int i = 0; i < num; i += 1) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVal = in.readDouble();
            double yyVal = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet b = new Planet(xxPos, yyPos, xxVal, yyVal, mass, img);
            allplanets[i] = b;
        }
        return allplanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);


        StdDraw.setScale(0 - radius, radius);
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        for (Planet p : ps) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

        double t = 0;

        while (t < T) {
            double[] xForces = new double[ps.length];
            double[] yForces = new double[ps.length];
            int i = 0;
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet p : ps) {
                xForces[i] = p.calcNetForceExertedByX(ps);
                yForces[i] = p.calcNetForceExertedByY(ps);
                p.update(dt, xForces[i], yForces[i]);
                p.draw();


            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet b : ps) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    b.xxPos, b.yyPos, b.xxVel,
                    b.yyVel, b.mass, b.imgFileName);


        }
    }

}