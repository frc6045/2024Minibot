// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swerve;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.TestMotorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestMotors extends SubsystemBase {
  /** Creates a new TestMotors. */
  private CANSparkMax testMotor1 = new  CANSparkMax(TestMotorConstants.kTestMotor1CANID, MotorType.kBrushless);
  public TestMotors() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
