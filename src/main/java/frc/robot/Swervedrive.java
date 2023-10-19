package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Swervedrive {
    

    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;

    public Swervedrive (WheelDrive backRight2, WheelDrive backLeft2, WheelDrive frontRight2, WheelDrive frontLeft2){
        this.backRight = backRight2;
        this.backLeft = backLeft2;
        this.frontRight = frontRight2;
        this.frontLeft = frontLeft2;
    }
    
    public void turn(double turnPower){
        frontLeft.SetDirection(45, turnPower);
        frontRight.SetDirection(135, turnPower);
        backLeft.SetDirection(225, turnPower);
        backRight.SetDirection(315, turnPower);
    }
}
