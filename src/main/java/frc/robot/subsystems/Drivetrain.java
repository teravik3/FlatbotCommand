// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(Constants.RIGHT_FRONT_CAN_ID);
  private final WPI_TalonSRX rightRearTalon = new WPI_TalonSRX(Constants.RIGHT_REAR_CAN_ID);
  private final WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(Constants.LEFT_FRONT_CAN_ID);
  private final WPI_TalonSRX leftRearTalon = new WPI_TalonSRX(Constants.LEFT_REAR_CAN_ID);

  //Create motor groups
  private final MotorControllerGroup rightDrive = new MotorControllerGroup(rightFrontTalon, rightRearTalon);
  private final MotorControllerGroup leftDrive = new MotorControllerGroup(leftFrontTalon, leftRearTalon);

  // Drivetrain
  private final DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

  public Drivetrain() {
    diffDrive.setDeadband(Constants.DEADBAND_SIZE);
    rightRearTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    leftRearTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
  }

  // public void init() {
  //   rightFrontTalon.setInverted(false);
  //   rightRearTalon.setInverted(false);
  //   // leftFrontTalon.setInverted(true);
  //   // leftRearTalon.setInverted(true);
  // // }
  
  // public void Drive(double x, double y) {
  //   rightFrontTalon.set(ControlMode.PercentOutput, y);
  //   rightRearTalon.set(ControlMode.PercentOutput, y);
  //   leftFrontTalon.set(ControlMode.PercentOutput, y);
  //   leftRearTalon.set(ControlMode.PercentOutput, y);
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.printf(" %f", getRightEncoderCount());
    diffDrive.arcadeDrive(
      RobotContainer.getJoyX(),
      RobotContainer.getJoyY()*Constants.SPEED_FACTOR
    );
  }

public void arcadeDrive(double m_speed, double m_speed2) {
  diffDrive.arcadeDrive(
    m_speed,
    m_speed2
  );
}

public double getRightEncoderCount() {
    return rightRearTalon.getSelectedSensorVelocity();
}

public double getLeftEncoderCount() {
    return leftRearTalon.getSelectedSensorVelocity();
}

public void resetEncoders() {
}

public int getRightDistanceInch() {
    return 0;
}

public int getLeftDistanceInch() {
    return 0;
}
public double getAverageDistanceInch() {
  return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
}
}
