package frc.robot;
/*dear god this is hell
though hell cowers in the face of my agony*/

public class RobotMap {
    //can drive motors; CAN IDS CORRECT
    public static final int R1CANID = 1;
    public static final int R2CANID = 2;
    public static final int L1CANID = 3;
    public static final int L2CANID = 4;

   
    
    //shooter motor controller IDs
    public static final int INDEXID = 7; 
    public static final int SHOOTID = 5; 
    public static final int SHOOTINTAKEID = 6; 

    //auto
        //auto speeds
            public static final double AUTOSHOOTINTAKESPEED = 0;
            public static final double AUTOINDEXERSPEED = 0;
            public static final double AUTOSHOOTSPEED = 0;
            public static final double AUTODRIVESPEEDFOR = 0;
            public static final double AUTODRIVESPEEDBAC = 0;

        //auto durations
            public static final double AUTOSPINUPSHOOTINIT = 2.5;
            public static final double AUTOSHOOTFIRST = AUTOSPINUPSHOOTINIT + 2.5;
            public static final double AUTODRIVEBACK = AUTOSHOOTFIRST + 4.9;
            public static final double AUTOSTOPDRIVE = AUTODRIVEBACK + .6;
            /*public static final double AUTODRIVEFOR = AUTOSTOPDRIVE + 3.9;
            public static final double AUTOSPINUPSHOOTSEC = AUTODRIVEFOR + AUTOSPINUPSHOOTINIT;
            public static final double AUTOSHOOTSEC = AUTOSPINUPSHOOTSEC + 2.5;
            public static final double AUTOSTOPSHOOTSEC = AUTOSHOOTSEC + 4.9;*/



    //ramping
    public static final double RAMP_VAL = 0.1;

    //tele op motor powers
    public static final double TELEOPINDEXSPEEDFOR = .6;
    public static final double TELEOPINDEXSPEEDBAC = -.2;
    public static final double TELEOPSHOOTINTAKESPEEDIN = -.4;
    public static final double TELEOPSHOOTINTAKESPEEDOUT = .4;
    public static final double TELEOPSHOOTSPEED = .65;

    //operator controls
    public static final int shootButton = 1;
    public static final int indexButton = 2;
    public static final int shootIntakeButtonIn = 3;
    public static final int shootIntakeButtonOut = 4;


    //driver controls

}
