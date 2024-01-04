// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.swerve.DriveSubsystem;

public class RobotContainer{
private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
private final XboxController m_driverController = new XboxController(0);
private Autos m_autos = new Autos(m_driveSubsystem);  
public RobotContainer() {

    m_driveSubsystem.setDefaultCommand(
    new RunCommand(
            () -> m_driveSubsystem.drive(
                MathUtil.applyDeadband(-m_driverController.getLeftY(), 0.15),
                MathUtil.applyDeadband(-m_driverController.getLeftX(), 0.15),
                MathUtil.applyDeadband(-m_driverController.getRightX(), 0.20),
                true),
            m_driveSubsystem));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(m_driverController, XboxController.Button.kStart.value).onTrue(new RunCommand(() -> { m_driveSubsystem.zeroHeading();}));
  }

  public Command getAutonomousCommand() {
    return m_autos.getAutonomousCommand();
  }
}
