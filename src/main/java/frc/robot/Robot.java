// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);
  XboxController pneumaticsTest = new XboxController(3);

  //drive motors
  CANSparkMax driveR1 = new CANSparkMax(RobotMap.R1CANID, MotorType.kBrushless);
  CANSparkMax driveR2 = new CANSparkMax(RobotMap.R2CANID, MotorType.kBrushless);
  CANSparkMax driveR3 = new CANSparkMax(RobotMap.R3CANID, MotorType.kBrushless);
  CANSparkMax driveL1 = new CANSparkMax(RobotMap.L1CANID, MotorType.kBrushless);
  CANSparkMax driveL2 = new CANSparkMax(RobotMap.L2CANID, MotorType.kBrushless);
  CANSparkMax driveL3 = new CANSparkMax(RobotMap.L3CANID, MotorType.kBrushless);


  MotorControllerGroup driveR = new MotorControllerGroup(driveR1, driveR2, driveR3);
  MotorControllerGroup driveL = new MotorControllerGroup(driveL1, driveL2, driveL3);

  DifferentialDrive drive = new DifferentialDrive(driveL, driveR);

   //shoot motors
   TalonSRX m_indexer = new TalonSRX(RobotMap.INDEXID);
   TalonFX m_shootIntake = new TalonFX(RobotMap.SHOOTINTAKEID);  
   TalonFX m_shoot = new TalonFX(RobotMap.SHOOTID);

   //intake motors
   TalonSRX m_feedleft = new TalonSRX(RobotMap.FEEDLEFTID);
   TalonSRX m_feedright = new TalonSRX(RobotMap.FEEDRIGHTID);
   TalonSRX m_intake = new TalonSRX(RobotMap.INTAKEID);
   TalonSRX m_climber = new TalonSRX(RobotMap.CLIMBID);

   //auto/timer stuffs
   Timer timer = new Timer();

   //pneumatics
   DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.SOLCHANNELFOR, RobotMap.SOLCHANNELBAC);
   //DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.SOLRIGHTCHANNELFOR, RobotMap.SOLRIGHTCHANNELBAC);
  // Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    /*compressor.start();
    compressor.enableDigital();*/
  }

  @Override
  public void robotPeriodic() {
    
    
  }

  @Override
  public void autonomousInit() {
    doubleSolenoid.set(Value.kReverse);
    timer.start();
    timer.reset();
  }

  @Override
  public void autonomousPeriodic() {
    
    if(timer.get()<RobotMap.AUTOSPINUPSHOOTINIT){
      m_shoot.set(ControlMode.PercentOutput, RobotMap.AUTOSHOOTSPEED);

    }
    if(timer.get()<RobotMap.AUTOSHOOTFIRST&& timer.get()> RobotMap.AUTOSPINUPSHOOTINIT){
      //shooter.set(RobotMap.AUTOSHOOTSPEED);
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.AUTOSHOOTINTAKESPEED);
      m_indexer.set(ControlMode.PercentOutput, RobotMap.AUTOINDEXERSPEED);


    }
    if(timer.get()>RobotMap.AUTOSHOOTFIRST &&timer.get()<RobotMap.AUTODRIVEBACK){
      m_shoot.set(ControlMode.PercentOutput, 0);
      m_shootIntake.set(ControlMode.PercentOutput, 0);
      m_indexer.set(ControlMode.PercentOutput, 0);


      
      drive.arcadeDrive(0, -.36);
      //intake.set(-0.7);
    }
    
    if(timer.get()>RobotMap.AUTODRIVEBACK && timer.get()<RobotMap.AUTOSTOPDRIVE){
      drive.arcadeDrive(0,0);
    }
  }

  @Override
  public void teleopInit() {
    driveR1.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveR2.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveL1.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveL2.setOpenLoopRampRate(RobotMap.RAMP_VAL);
  }

  @Override
  public void teleopPeriodic() {
    /*troubleshooting: 
1. push code without driverReverseAll, possible confusion with elses
2. return to allShoot, reevaluate
    */
    speedButtons();
    operatorAllMethods();
    deployRetractIntake();
    climb();
    //driverReverseAll();
    //shootIntake();
    //shoot();
   // feed();
    //indexer();
    /*drive.arcadeDrive(driver.getRawAxis(0) * 0.8, driver.getRawAxis(3) * 0.8);
    if(driver.getRawAxis(2) > 0){
      drive.arcadeDrive(driver.getRawAxis(0) * 0.8, -driver.getRawAxis(2) * 0.8);
    }*/
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  public void operatorAllMethods(){
    /*
    trigger throttle shooter
    intake button
    shoot button
    reverse button
    */
    //intake method
    if(operator.getRawButton(RobotMap.OPERATORALLINTAKEBUTTON)){
      //Y
      //run intake, no intake yet
      m_intake.set(ControlMode.PercentOutput, RobotMap.TELEOPINTAKESPEEDIN);
      m_feedleft.set(ControlMode.PercentOutput, RobotMap.INTAKEFEEDLEFTSPEED);
      m_feedright.set(ControlMode.PercentOutput, -RobotMap.INTAKEFEEDLEFTSPEED);
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDIN);
      m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPINDEXSPEEDBAC);
    }
//shoot method
    if(operator.getRawAxis(RobotMap.shootAxis)>0){
      //L trigger
      m_shoot.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTSPEED);
      m_shootIntake.set(ControlMode.PercentOutput, -RobotMap.TELEOPSHOOTINTAKESPEEDIN);
    }
    if(operator.getRawAxis(3)>0){
      m_shoot.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTSPEED);
    }
   /* if(operator.getRawButton(RobotMap.OPERATORALLSHOOTBUTTON)){
   
      //if(timer.get() < RobotMap.THROTTLEUPTIME){
        //m_shoot.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTSPEED);
      //}
      //if(timer.get() > RobotMap.THROTTLEUPTIME){
        m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDIN);
        m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPINDEXSPEEDFOR);
      //}
    }*/
    //reverse button, moved so as not to interfere with other methods
   /* if(driver.getRawButton(RobotMap.DRIVERREVERSEBUTTON)){
      m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPREVERSEINDEXSPEED);
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDOUT);
      //reverse intake, when intake is done
      //m_intake.set(ControlMode.PercentOutput, RobotMap.TELEOPINTAKESPEEDOUT);
    }*/
    if(!operator.getRawButton(RobotMap.OPERATORALLINTAKEBUTTON) && operator.getRawAxis(RobotMap.shootAxis)==0 && operator.getRawAxis(3)==0){
      m_feedleft.set(ControlMode.PercentOutput, 0);
      m_feedright.set(ControlMode.PercentOutput, 0);
      m_shootIntake.set(ControlMode.PercentOutput, 0);
      m_indexer.set(ControlMode.PercentOutput, 0);
      m_shoot.set(ControlMode.PercentOutput, 0);
      m_intake.set(ControlMode.PercentOutput, 0);
     
    }
  }

  public void speedButtons(){
    //slow button for xbox controller
   if(driver.getRawButton(3)){
    drive.arcadeDrive(driver.getRawAxis(0) * 0.2, driver.getRawAxis(3) * 0.2);
    if(driver.getRawAxis(2) > 0){
    drive.arcadeDrive(driver.getRawAxis(0) * 0.2, -driver.getRawAxis(2) * 0.2);
    }
  }

 //fast button for xbox controller
  else if(driver.getRawButton(1)){
    drive.arcadeDrive(driver.getRawAxis(0), driver.getRawAxis(3));
    if(driver.getRawAxis(2)>0){
      drive.arcadeDrive(driver.getRawAxis(0), -driver.getRawAxis(2));
    }
  }

 //default condition for neither buttons active
  else if(!driver.getRawButton(3) || !driver.getRawButton(1)){
    drive.arcadeDrive(driver.getRawAxis(0) * 0.8, driver.getRawAxis(3) * 0.8);
    if(driver.getRawAxis(2) > 0){
      drive.arcadeDrive(driver.getRawAxis(0) * 0.8, -driver.getRawAxis(2) * 0.8);
    }
  }
} 


public void shoot(){
  if(operator.getRawAxis(RobotMap.shootAxis)>0){
    m_shoot.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTSPEED);
  }
else{m_shoot.set(ControlMode.PercentOutput, 0);}}
 public void shootIntake(){
    m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDIN*operator.getRawAxis(RobotMap.shootIntakeAxis));
    
    //shootIntake
    //if shootintake in, indexer spins opposite direction
   /*if(operator.getRawButton(RobotMap.shootIntakeButtonIn)){
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDIN);
   }
    if(operator.getRawButton(RobotMap.shootIntakeButtonOut)){
        m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDOUT);
    }
   else{m_shootIntake.set(ControlMode.PercentOutput, 0);}
   */
  }
    //shoot intake out
    /*if(operator.getRawButton(RobotMap.shootIntakeButtonOut)){
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDOUT);
     m_indexer.set(ControlMode.PercentOutput, 0);
    }*/

    
    //index
    
    /*if(operator.getRawAxis(RobotMap.indexAxis)>0){
      m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPINDEXSPEEDFOR);
    }*/
    /*if(operator.getRawAxis(RobotMap.indexAxis)==0){
      if(operator.getRawAxis(RobotMap.shootIntakeAxis)>0){
        m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPINDEXSPEEDBAC);
      }
    }*/
    //shoot
    /*
    else{
      m_indexer.set(ControlMode.PercentOutput, 0);
      m_shoot.set(ControlMode.PercentOutput, 0);
    }*/
    
    
    /*in the event that the above else{} statement is janky, use a really long one of these
    if(!operator.getRawButton(RobotMap.shootIntakeButtonIn) && !operator.getRawButton(RobotMap.shootIntakeButtonOut)){
      m_shootIntake.set(0.0);
    }*/
  //}

  /*public void driverReverseAll(){
    if(driver.getRawButton(RobotMap.DRIVERREVERSEBUTTON)){
      m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPREVERSEINDEXSPEED);
      m_shootIntake.set(ControlMode.PercentOutput, RobotMap.TELEOPSHOOTINTAKESPEEDOUT);
      //reverse intake, when intake is done
      //m_intake.set(ControlMode.PercentOutput, RobotMap.TELEOPINTAKESPEEDOUT);
    }
    else{
      m_indexer.set(ControlMode.PercentOutput, 0);
      m_shootIntake.set(ControlMode.PercentOutput, 0);
      //m_intake.set(ControlMode.PercentOutput, 0);

    }
  }*/

  public void feed(){
    
    if(driver.getRawButton(RobotMap.feedButton)){
      m_feedleft.set(ControlMode.PercentOutput, RobotMap.INTAKEFEEDLEFTSPEED);
      m_feedright.set(ControlMode.PercentOutput, -RobotMap.INTAKEFEEDLEFTSPEED);
    }
    if(!driver.getRawButton(RobotMap.feedButton)){
      m_feedleft.set(ControlMode.PercentOutput, 0);
      m_feedright.set(ControlMode.PercentOutput, 0);
    }
    
  }
public void indexer(){
  if(operator.getRawAxis(RobotMap.indexAxis)>0){
    m_indexer.set(ControlMode.PercentOutput, RobotMap.TELEOPINDEXSPEEDFOR);
  }
  if(operator.getRawAxis(RobotMap.indexAxis)==0){
    m_indexer.set(ControlMode.PercentOutput, 0);
  }
}
  public void deployRetractIntake(){
    
    if(operator.getRawButton(RobotMap.deployRetractIntakeOut)){
      //A
      doubleSolenoid.set(Value.kForward);
    }
    else if(operator.getRawButton(RobotMap.deployRetractIntakeIn)){
      //B
      doubleSolenoid.set(Value.kReverse);
    }
    /*if(!pneumaticsTest.getRawButton(RobotMap.intakeOutButton) && !pneumaticsTest.getRawButton(RobotMap.intakeInButton)){
      doubleSolenoid.set(Value.kOff);
    }*/
  }
  public void climb(){
    if(operator.getPOV() == 0){
      m_climber.set(ControlMode.PercentOutput, -1);
    }
    if(operator.getPOV() == 180){
      m_climber.set(ControlMode.PercentOutput, 1);
    }
    if(operator.getPOV()!=0 && operator.getPOV()!= 180){
      m_climber.set(ControlMode.PercentOutput, 0);
    }

  }
}