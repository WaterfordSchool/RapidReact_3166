package frc.robot;
/*dear god this is hell
though hell cowers in the face of my agony*/

public class RobotMap {
    //CAN drive motors; CAN IDS CORRECT
    public static final int R1CANID = 1;
    public static final int R2CANID = 2;
    public static final int R3CANID = 5;
    public static final int L1CANID = 3;
    public static final int L2CANID = 4;
    public static final int L3CANID = 6;


    //shooter motor controller IDs
    public static final int INDEXID = 23; 
    public static final int SHOOTID = 24; 
    public static final int SHOOTINTAKEID = 25; 
    

    //intake motor controller ids
    public static final int FEEDLEFTID = 11;
    public static final int FEEDRIGHTID = 10;
    public static final int INTAKEID = 12;

    //climb
    public static final int CLIMBID = 8;

    //auto
        //auto speeds
            public static final double AUTOSHOOTINTAKESPEED = RobotMap.TELEOPSHOOTINTAKESPEEDIN;
            public static final double AUTOINDEXERSPEED = RobotMap.TELEOPINDEXSPEEDFOR;
            public static final double AUTOSHOOTSPEED = RobotMap.TELEOPSHOOTSPEED;
            public static final double AUTODRIVESPEEDFOR = 0;
            public static final double AUTODRIVESPEEDBAC = 0;
            public static final double THROTTLEUPTIME = .6;

        //auto durations
            public static final double AUTOSPINUPSHOOTINIT = 2.5;
            public static final double AUTOSHOOTFIRST = AUTOSPINUPSHOOTINIT + 2.5;
            public static final double AUTODRIVEBACK = AUTOSHOOTFIRST + 4.9;
            public static final double AUTOSTOPDRIVE = AUTODRIVEBACK + .6;
            public static final double AUTODRIVEFOR = AUTOSTOPDRIVE + 4.9;
            public static final double AUTOSPINUPSHOOTSEC = AUTODRIVEFOR + AUTOSPINUPSHOOTINIT;
            public static final double AUTOSHOOTSEC = AUTOSPINUPSHOOTSEC + 2.5;
            public static final double AUTOSTOPSHOOTSEC = AUTOSHOOTSEC + 4.9;

    //ramping
    public static final double RAMP_VAL = 0.1;

    //tele op motor powers
    public static final double TELEOPINDEXSPEEDFOR = -.9;
    public static final double TELEOPINDEXSPEEDBAC = .2;
    public static final double TELEOPSHOOTINTAKESPEEDIN = .4;
    public static final double TELEOPSHOOTINTAKESPEEDOUT = -.4;
    public static final double TELEOPSHOOTSPEED = .65;
    public static final double INTAKEFEEDLEFTSPEED = 0.7;
    public static final double TELEOPINTAKESPEEDIN = .7;
    public static final double TELEOPINTAKESPEEDOUT = -.7;

    //tele op reverse speeds
    public static final double TELEOPREVERSEINDEXSPEED = .5;

    //operator controls
    public static final int OPERATORALLINTAKEBUTTON = 4;
    public static final int OPERATORALLSHOOTBUTTON = 3;

    //left trigger spinup shoot
    public static final int shootAxis = 2;

    //right trigger index
    public static final int indexAxis = 3;

    public static final int shootIntakeAxis = 1;
    public static final int shootIntakeButtonIn = 3;
    public static final int shootIntakeButtonOut = 4;

    public static final int feedButton = 9; 
    public static final int deployRetractIntakeIn = 1;
    public static final int deployRetractIntakeOut = 2;
    public static final int intakeOutButton = 5;
    public static final int intakeInButton = 6;

    //reverse
    public static final int DRIVERREVERSEBUTTON = 2;

    //pneumatics
    public static final int SOLCHANNELFOR = 0;
    public static final int SOLCHANNELBAC = 1;

    //leds
    public static final int LEDSPORT = 0;
    
    public static final int AMOUNTOFPATIENCEIHAVELEFT = 0;

}
