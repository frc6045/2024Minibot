package frc.robot;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.FeedForwardCharacterization;
import frc.robot.commands.FeedForwardCharacterization.FeedForwardCharacterizationData;
import frc.robot.subsystems.TestMotors;
import frc.robot.subsystems.swerve.DriveSubsystem;

public class Autos {
    private final DriveSubsystem m_drivetrainSubsystem;
    private final TestMotors m_TestMotors;
    private SendableChooser<String> autoChooser;
    private HashMap<String, List<Command>> m_commandMap;
   
    ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");
    public Autos(DriveSubsystem drivetrainSubsystem, TestMotors testMotors){
        m_drivetrainSubsystem = drivetrainSubsystem;
        m_TestMotors = testMotors;
        autoChooser = new SendableChooser<>();
        m_commandMap = new HashMap<>();
        
        autoChooser.addOption("Drivetrain Characterization", "Characterization");
        autoChooser.addOption("Basic Test Auto", "Basic Test Auto");
        autoChooser.addOption("3RingPath", "3RingPath");
        m_commandMap.put("Characterization", List.of(
            new FeedForwardCharacterization(m_drivetrainSubsystem, true, new FeedForwardCharacterizationData("DriveSubsystem"), 
            m_drivetrainSubsystem::runCharacterizationVolts, m_drivetrainSubsystem::getCharacterizationVelocity),
            new FeedForwardCharacterization(m_drivetrainSubsystem, true, new FeedForwardCharacterizationData("DriveSubsystem"), 
            m_drivetrainSubsystem::runCharacterizationVolts, m_drivetrainSubsystem::getCharacterizationVelocity)));
        m_commandMap.put("Flywheel Charaterization", List.of(new FeedForwardCharacterization(m_TestMotors, true, new FeedForwardCharacterizationData("TestMotors"), (volts) -> {m_TestMotors.runCharacterization(0, volts);}, 
        () -> {return m_TestMotors.getCharacterizationVelocity(0);})));
        m_commandMap.put("Basic Test Auto", List.of(AutoBuilder.buildAuto("Basic Test Auto")));
        m_commandMap.put("3RingPath", List.of(AutoBuilder.buildAuto("3RingPath")));
        // SmartDashboard.putData(autoChooser);
        autoTab.add(autoChooser);
        
    }
 

    public Command getAutonomousCommand() {
        String auto = autoChooser.getSelected();
        return m_commandMap.get(auto).get(0);
    }
}
