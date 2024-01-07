// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  /** Creates a new Camera. */
  private final PhotonCamera m_Camera;
  public Camera() {
    m_Camera = new PhotonCamera("photonvision");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
