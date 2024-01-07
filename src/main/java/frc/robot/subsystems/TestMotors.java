// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import Jama.test.TestMatrix;
import frc.robot.Constants.TestMotorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestMotors extends SubsystemBase {
  /** Creates a new TestMotors. */
  private final CANSparkMax m_testMotor1;
  private final CANSparkMax m_testMotor2;
  private final RelativeEncoder m_testMotor1Encoder;
  private final RelativeEncoder m_testMotor2Encoder; 
 
  public TestMotors() {
    m_testMotor1 = new CANSparkMax(TestMotorConstants.kTestMotor1CANID, MotorType.kBrushless);
    m_testMotor2 = new CANSparkMax(TestMotorConstants.kTestMotor2CANID, MotorType.kBrushless);
    m_testMotor1Encoder = m_testMotor1.getEncoder();
    m_testMotor2Encoder = m_testMotor2.getEncoder();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void runMotors(Supplier<Double> speed1, Supplier<Double> speed2){
    //System.out.println(speed1.get() + " " + speed2.get()); //see if deadband worked!
    if(speed1.get() <= TestMotorConstants.kTestMotor1MaxSpeed){
    
      m_testMotor1.set(speed1.get());
    } else {
      m_testMotor1.set(TestMotorConstants.kTestMotor1MaxSpeed);
    }
    if(speed2.get() <= TestMotorConstants.kTestMotor2MaxSpeed){
      m_testMotor2.set(-speed2.get());
    } else {
      m_testMotor2.set(-TestMotorConstants.kTestMotor2MaxSpeed);
    }
  }

  public void OneSupplierRunMotors(Supplier<Double> speed){
   if(speed.get() <= TestMotorConstants.kTestMotor1MaxSpeed){
      m_testMotor1.set(speed.get());
    } else {
      m_testMotor1.set(TestMotorConstants.kTestMotor1MaxSpeed);
    }
    if(speed.get() <= TestMotorConstants.kTestMotor2MaxSpeed){
      m_testMotor2.set(-speed.get());
    } else {
      m_testMotor2.set(-TestMotorConstants.kTestMotor2MaxSpeed);
    }

  }

  public void stop(){
    m_testMotor1.set(0);
    m_testMotor2.set(0);

  }

  public void setVoltage1(double volts){
    m_testMotor1.setVoltage(volts);
  }
  public void setVoltage2(double volts){
    m_testMotor2.setVoltage(volts);
  }
  public void runCharacterization(int motorIndex, double volts){
    switch(motorIndex){
      case 0:
        setVoltage1(volts);
      case 1:
        setVoltage2(volts);
      default:
        System.out.println("use acutal index");
    }
  }
  public double getCharacterizationVelocity(int motorIndex){
    switch(motorIndex){
      case 0:
        return m_testMotor1Encoder.getVelocity() * 2 * Math.PI;
      case 1:
        return m_testMotor2Encoder.getVelocity() * 2 * Math.PI;
      default:
        return 0;
  }
}
}
