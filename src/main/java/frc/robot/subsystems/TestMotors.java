// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.TestMotorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestMotors extends SubsystemBase {
  /** Creates a new TestMotors. */
  private CANSparkMax m_testMotor1;
  private CANSparkMax m_testMotor2;
  public TestMotors() {
    m_testMotor1 = new CANSparkMax(TestMotorConstants.kTestMotor1CANID, MotorType.kBrushless);
    m_testMotor2 = new CANSparkMax(TestMotorConstants.kTestMotor2CANID, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void runMotors(Supplier<Double> speed1, Supplier<Double> speed2){
    m_testMotor1.set(speed1.get());
    m_testMotor2.set(speed2.get());
  }


}
