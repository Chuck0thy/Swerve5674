package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;


public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private PIDController pidController;
    private CANCoder encoder;
    
    private final double MAX_VOLTS = 4.95;
    public WheelDrive(int angleMotor, int speedMotor, int encoderID) {
        this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

        pidController = new PIDController(.02, .001, 0);
        encoder = new CANCoder(encoderID);

        pidController.enableContinuousInput(-180, 180);        
    }

    public void drive(double speed, double angle){
        //speedMotor.set(speed);

        

       

        // this.pidController.setSetpoint(setpoint);
        
        //double angleMotorDistanceTraveled = this.encoder.getPosition() * ((150/7)*(2*(Math.PI*4)));
        
    } 

    public void Reset() {
        pidController.reset();
    }

    public void SetDirection(double angle, double speed) {
        // this.speedMotor.set(speed*.2);
        
        this.speedMotor.set(speed);

        double position = -(this.encoder.getAbsolutePosition());
        // position += 45;

        MathHelp.pickCloserAngle(position, angle, -angle);
        
        double pidOutput = pidController.calculate(position, angle);
        pidOutput *= 0.45;
        
        // if (MathHelp.isEqualApprox(angle, position, 1))
        // {
        //     pidOutput = 0;
        // }

        this.angleMotor.set((pidOutput));
        SmartDashboard.putNumber("pid number", pidOutput);
        SmartDashboard.putNumber("encoder pos", position);

        
    }

    public void setAngle(double angle) {
        double position = -(this.encoder.getAbsolutePosition());
        // position += 45;

        MathHelp.pickCloserAngle(position, angle, -angle);
        
        double pidOutput = pidController.calculate(position, angle);
        pidOutput *= 0.45;
        
        if (MathHelp.isEqualApprox(angle, position, .25))
        {
            pidOutput = 0;
        }

        this.angleMotor.set((pidOutput));
    }
    
    
    

}