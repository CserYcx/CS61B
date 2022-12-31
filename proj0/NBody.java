import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NBody {
    /*Some Tips about the static function:
      1. The static function and members will be loaded in memory with the class defined,
      Otherwise the non-static function have the code segment just with an instance built
    */

    /**read the planets.txt and return the radius length
    /**here you should use the static function because may be no an instance in the class
    /**if function is not static, it only used when an instance exists */

    //return the txt line numbers
    public static double readRadius(String path){
        In in = new In(path);

        int planets_num = in.readInt();
        double radius   = in.readDouble();
        return radius;
    }

    /**Read all planets class members */
    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int sum = 5;

        int num = in.readInt();
        double radius = in.readDouble();

        Planet[] allplanets  = new Planet[sum];

        int cnt = 0;
        while(cnt<sum){
            double xxPos = in.readDouble();
            //System.out.println(xxPos);
            double yyPos = in.readDouble();
            //System.out.println(yyPos);
            double xxVel = in.readDouble();
            //System.out.println(xxVel);
            double yyVel = in.readDouble();
            //System.out.println(yyVel);
            double mass  = in.readDouble();
            //System.out.println(mass);
            String imgFileName = in.readString();
            //System.out.println(imgFileName);
            allplanets[cnt] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
            cnt++;
        }
        //System.out.println(cnt);   <-   that's a test
        return allplanets;
    }


    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("The parameter is not enough!");
        }

        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];

        //get all planets in filename
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        //Some parameter for the StdDraw
        int canvasWidth  = 512;
        int canvasHeight = 512;
        String backGroundImg = "./images/starfield.jpg";

        //Use the StdDraw class to draw picture
        //Here a bug: If i use the setCanvasSize, the compiler tell me
        //the API is out of date(why!!)
        //The newest: when i put some parameter in setCanvasSize, that's no error
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(-canvasWidth, canvasHeight);
        StdDraw.setYscale(-canvasWidth, canvasHeight);
        StdDraw.picture(0,0,backGroundImg);
        for(int i=0;i<planets.length;++i){
            planets[i].draw();
        }
        //Let's create a animation
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        StdDraw.enableDoubleBuffering();
        for(int time=0;time<T;++time){
            // 1.Evaluate x and y forces
            // 2.update each planets attributes
            // 3.Draw background and planets
            // 4.Show offscreen buffer
            // 5.Pause animation for 10 milliseconds
            // 6.Increase your dt

            //This function is to avoid flicked

            //Evaluate x and y forces
            for(int i=0;i<planets.length;++i){
               xForces[i] = planets[i].calcNetForceExertedByX(planets);
               yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            //Update each planets attributes
            for(int i=0;i<planets.length;++i){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            //Draw Background
            StdDraw.picture(0,0,backGroundImg);
            //Draw all planets
            for(int i=0;i<planets.length;++i){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            dt++;
        }
    }

}
