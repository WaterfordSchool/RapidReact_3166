// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
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

  //drive motors
  CANSparkMax driveR1 = new CANSparkMax(RobotMap.R1CANID, MotorType.kBrushless);
  CANSparkMax driveR2 = new CANSparkMax(RobotMap.R2CANID, MotorType.kBrushless);
  CANSparkMax driveL1 = new CANSparkMax(RobotMap.L1CANID, MotorType.kBrushless);
  CANSparkMax driveL2 = new CANSparkMax(RobotMap.L2CANID, MotorType.kBrushless);


  MotorControllerGroup driveR = new MotorControllerGroup(driveR1, driveR2);
  MotorControllerGroup driveL = new MotorControllerGroup(driveL1, driveL2);

  DifferentialDrive drive = new DifferentialDrive(driveL, driveR);

   //shoot motors
   TalonSRX m_indexer = new TalonSRX(RobotMap.INDEXID);
   CANSparkMax m_shootIntake = new CANSparkMax(RobotMap.SHOOTINTAKEID, MotorType.kBrushless);
   CANSparkMax m_shoot = new CANSparkMax(RobotMap.SHOOTID, MotorType.kBrushless);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    driveR1.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveR2.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveL1.setOpenLoopRampRate(RobotMap.RAMP_VAL);
    driveL2.setOpenLoopRampRate(RobotMap.RAMP_VAL);
  }

  @Override
  public void teleopPeriodic() {
    speedButtons();
    //allShoot();
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

  public void speedButtons(){
    //slow button for xbox controller
   if(driver.getRawButton(3)){
    drive.arcadeDrive(-driver.getRawAxis(0) * 0.2, -driver.getRawAxis(3) * 0.2);
    if(driver.getRawAxis(2) > 0){
    drive.arcadeDrive(-driver.getRawAxis(0) * 0.2, driver.getRawAxis(2) * 0.2);
    }
  }

 //fast button for xbox controller
  else if(driver.getRawButton(1)){
    drive.arcadeDrive(-driver.getRawAxis(0), -driver.getRawAxis(3));
    if(driver.getRawAxis(2)>0){
      drive.arcadeDrive(-driver.getRawAxis(0), driver.getRawAxis(2));
    }
  }

 //default condition for neither buttons active
  else if(!driver.getRawButton(3) || !driver.getRawButton(1)){
    drive.arcadeDrive(-driver.getRawAxis(0) * 0.8, -driver.getRawAxis(3) * 0.8);
    if(driver.getRawAxis(2) > 0){
      drive.arcadeDrive(-driver.getRawAxis(0) * 0.8, driver.getRawAxis(2) * 0.8);
    }
  }
} 
public void arcadeDrive(){
  //1 = speed
  //4 = turn
  drive.arcadeDrive(-driver.getRawAxis(4), -driver.getRawAxis(1));
}

  public void allShoot(){
    //shootIntake
    if(operator.getRawButton(RobotMap.shootIntakeButtonIn)){
      m_shootIntake.set(-.4);
      //m_indexer.set(ControlMode.PercentOutput, -0.2);
    }
    if(operator.getRawButton(RobotMap.shootIntakeButtonOut)){
      m_shootIntake.set(.4);
     // m_indexer.set(ControlMode.PercentOutput, 0);
    }
    //index
    if(operator.getRawButton(RobotMap.indexButton)){
     // m_indexer.set(ControlMode.PercentOutput, .6);
    }
    //shoot
    if(operator.getRawButton(RobotMap.shootButton)){
      m_shoot.set(.65);
    }
    else{
     // m_indexer.set(ControlMode.PercentOutput, 0);
      m_shootIntake.set(0.0);
      m_shoot.set(0);
    }
    /*in the event that the above else{} statement is janky, use a really long one of these
    if(!operator.getRawButton(RobotMap.shootIntakeButtonIn) && !operator.getRawButton(RobotMap.shootIntakeButtonOut)){
      m_shootIntake.set(0.0);
    }*/
  }
}
