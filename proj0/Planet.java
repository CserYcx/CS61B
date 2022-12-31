public class Planet {
    double xxPos;           // x position
    double yyPos;           // y position
    double xxVel;           // x velocity
    double yyVel;           // y velocity
    double mass;            // the planet mass
    String imgFileName;     // the name of file corresponds to the image

    // Initialize
    public Planet(double xxPos,double yyPos,double xxVel,double yyVel,double mass,String imgFileName){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    // a identical Planet object copy
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //Calculate the distance between two planet
    public double calcDistance(Planet p){
        double dis_sqaurex = Math.pow(p.xxPos-this.xxPos,2);
        double dis_sqaurey = Math.pow(p.yyPos-this.yyPos,2);
        double dis_sqaure = dis_sqaurex+dis_sqaurey;
        return Math.pow(dis_sqaure,0.5);
    }

    static final double G = 6.67e-11;
    //Calculate the force exerted on planet
    public double calcForceExertedBy(Planet p){
        double distance = this.calcDistance(p);
        double dis_sqaure = Math.pow(distance,2);
        return G*this.mass*p.mass/dis_sqaure;
    }

    //Calculate the force in x coordinate
    public double calcForceExertedByX(Planet p){
        double total_force = this.calcForceExertedBy(p);
        double x_force = total_force * ((p.xxPos-this.xxPos)/this.calcDistance(p));
        return x_force;
    }

    //Calculate the force in y
    public double calcForceExertedByY(Planet p) {
        double total_force = this.calcForceExertedBy(p);
        double y_force = total_force * ((p.yyPos - this.yyPos) / this.calcDistance(p));
        return y_force;
    }

    //Calculate the Net force in x coordinate
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double total_x_force = 0;
        for(int i=0;i<allPlanets.length;++i){
            if(this == allPlanets[i]){
                continue;
            }
            else{
                total_x_force = total_x_force + this.calcForceExertedByX(allPlanets[i]);
            }
        }
        return total_x_force;
    }

    //Calculate the Net force in y coordinate
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double total_y_force = 0;
        for(int i=0;i<allPlanets.length;++i){
            if(this == allPlanets[i]){
                continue;
            }
            else{
                total_y_force = total_y_force + this.calcForceExertedByY(allPlanets[i]);
            }
        }
        return total_y_force;
    }

    //Update the velocity
    public void update(double dt, double xforce, double yforce){
        //Calculate the acceleration
        double ax = xforce/this.mass;
        double ay = yforce/this.mass;

        //New velocity
        this.xxVel = this.xxVel + dt*ax;
        this.yyVel = this.yyVel + dt*ay;

        //New position
        this.xxPos = this.xxPos + dt*this.xxVel;
        this.yyPos = this.yyPos + dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos/1E9,this.yyPos/1E9,"./images/"+this.imgFileName);
    }
}
