package org.firstinspires.ftc.teamcode;


class Robot extends TimedRobot {
    private Servo servo;
    private Joystick joystick;


    @Override
    public void robotInit() {
        servo = new Servo(0);  // Servo on PWM port 0
        joystick = new Joystick(0);  // Joystick on USB port 0
    }


    @Override
    public void teleopPeriodic() {
        // Map joystick axis (for example, Y-axis) to the servo position
        double joystickInput = 0;  // Range: -1 to 1
        joystick.getY();
        double servoPosition = (joystickInput + 1) / 2;  // Map range to 0 to 1
        servo.set(servoPosition);


        // Optionally, display the current servo position on the dashboard
        Object SmartDashboard = null;
        SmartDashboard.getClass();
    }


    private class Servo {
        public Servo(int i) {
        }


        public void set(double servoPosition) {
        }
    }
}





