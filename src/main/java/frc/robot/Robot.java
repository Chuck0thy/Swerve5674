// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.MotorType;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  String m_autoSelected;
  int masterWheelAngle = 0;
  final SendableChooser<Integer> m_chooser = new SendableChooser<>();

  final XboxController m_driverController = new XboxController(0);

  //final CANSparkMax spark = new CANSparkMax(0, null);
  
  // private ShuffleboardTab tab = new Shuffleboard.get

  private WheelDrive backRight = new WheelDrive(5, 6, 11);
  private WheelDrive backLeft = new WheelDrive(7, 8, 10);
  private WheelDrive frontRight = new WheelDrive(3, 4, 12);
  private WheelDrive frontLeft = new WheelDrive(1, 2, 9);

  private TestDrive testyDrive = new TestDrive(backRight, backLeft, frontRight, frontLeft);
  
  // private Swervedrive swervedrive = new Swervedrive(backRight, backLeft, frontRight, frontLeft);
  // private Swervedrive swervedrive = new Swervedrive(null, null, null, frontLeft);
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    
    
    

    


    m_chooser.setDefaultOption("Straight", 180);
    m_chooser.addOption("Turn", 90);
    SmartDashboard.putData("Auto choices", m_chooser);
    SmartDashboard.putNumber("angle", 0);
    
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    masterWheelAngle = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }

    frontLeft.setAngle(0);
    frontRight.setAngle(0);
    backLeft.setAngle(0);
    backRight.setAngle(0);

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    SmartDashboard.putNumber("angle", 0);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {



    
    double x = m_driverController.getLeftX();
    double y = -m_driverController.getLeftY();

    double rX = m_driverController.getRightX();

    double speed = Math.sqrt((x*x) + (y*y));

    double angle = -Math.atan(x/1);

    angle *= 100;

    // angle -= 45;

    
    if (x == 0 && y == 0){
      angle = 0;
    }

    // angle = 90;

    // speed = 0;
    // if(x < 0) {
    //   if (angle > 0) {
    //     angle = -angle;
    //   }
    // }
    // else {
    //   if (angle < 0) {
      //     angle = Math.sqrt(angle*angle);

    //   }
    // }
    
    // angle *= .5;
    SmartDashboard.putNumber("Raw angle", angle);
    
    if (x>=0 && y>=0){ //1
      angle = 90 - angle - 90;
    }
    if(x>0 && y<0){//2
      angle = angle + 180;
    }
    if(x<0 && y>0){//4
      angle = 90-angle + 270;
    }
    if(x<0 && y<0){//3
      angle = angle + 180;
    }
    if(y < 0 && angle == 0){
      angle = 180;
    }
    if(y == 0 && x < 0) {
      angle = 270;
    }
    angle = Math.abs(angle);
    // angle = Math.sqrt(angle*angle);
    // angle *= 2;
    // angle = 0;
    //speed *= 0.0;
    if(rX == 0){
    frontLeft.SetDirection(angle, speed);
    frontRight.SetDirection(angle, speed);
    backLeft.SetDirection(angle, speed);
    backRight.SetDirection(angle, speed);
    } else {
      frontLeft.SetDirection(45, rX);
      frontRight.SetDirection(135, rX);
      backLeft.SetDirection(315, rX);
      backRight.SetDirection(225, rX);
    }
    
    
    SmartDashboard.putNumber("angle", angle);
    
    // swervedrive.drive(m_driverController.getLeftX(), m_driverController.getLeftY(), m_driverController.getRightX());
  }
  
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }
  
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    SmartDashboard.putNumber("read angle", 90);
    // testyDrive.setToAngle(90);
    testyDrive.setToAngle(SmartDashboard.getNumber("read angle", 0));
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
