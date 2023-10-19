package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class TestDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private PIDController pidController;
    private CANCoder encoder;

    public final double L = 24.25;
    public final double W = 24.25;

    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;

    public TestDrive(WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.backRight = backRight;
        this.backLeft = backLeft;
    }




    public void wheels(int angleMotor, int speedMotor, int encoderID) {
        this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

        pidController = new PIDController(.02, .001, 0);
        encoder = new CANCoder(encoderID);

        pidController.enableContinuousInput(-180, 180);        
    }




    public void setToAngle(double angle) {

        frontLeft.setAngle(angle);
        frontRight.setAngle(angle);
        backLeft.setAngle(angle);
        backRight.setAngle(angle);
    }

    public void drive(double power, double angle, double turnPower){
        
            if (turnPower == 0){
                frontLeft.SetDirection(angle, power);
                frontRight.SetDirection(angle, power);
                backLeft.SetDirection(angle, power);
                backRight.SetDirection(angle, power);
            }else{
                frontLeft.SetDirection(45, turnPower);
                frontRight.SetDirection(135, turnPower);
                backLeft.SetDirection(225, turnPower);
                backRight.SetDirection(315, turnPower);
            }
        
    }
}

        
    
