package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;


public class DriveMethods extends OpMode {
    Devices Robot = new Devices();
    private DcMotor viperSlideMotor;
    public Limelight3A limelight;


    // Define target positions for the slide (in encoder counts)
    private static final int SLIDE_BOTTOM_POSITION = 0;
    private static final int SLIDE_MID_POSITION = -8;  // adjust based on your setup
    private static final int SLIDE_TOP_POSITION = -15;  // adjust based on your setup


    // Position for the Viper slide
    int Current_pos = 0;


    @Override
    public void init() {
        telemetry.addData("I am inside Init", null);
        telemetry.update();
        // Viper_init();
    }

    @Override
    public void loop() {
    }


    public void OmniDrive(double y, double x, double rx, double factor) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double FrontLeftPower = (y + x + rx) / denominator;
        double BackLeftPower = (y - x + rx) / denominator;
        double FrontRightPower = (y - x - rx) / denominator;
        double BackRightPower = (y + x - rx) / denominator;


        Robot.LeftFront.setPower(FrontLeftPower * factor);
        Robot.RightFront.setPower(FrontRightPower * factor);
        Robot.LeftBack.setPower(BackLeftPower * factor);
        Robot.RightBack.setPower(BackRightPower * factor);


        LLResult Robot_pos;
        Robot_pos = getLatestRobotPosition();
        telemetry.addData("Robot_pos",Robot_pos);
        telemetry.update();
        if (Robot_pos != null) {
            double steering = Robot_pos.getTx() * rx;
            double driving = Robot_pos.getTy() * rx;
            //Robot.setDrive(steering, driving);
            telemetry.addData("Robot Position Tx",Robot_pos.getTx());
            telemetry.addData("ty:",Robot_pos.getTy());
            telemetry.addData("with rx",steering);
            telemetry.addData("with rx",driving);
            telemetry.update();
        }


    }


    public double square(double x) {
        double result;
        if (x < 0) {
            result = -1 * x * x;
            return result;
        }
        result = x * x;
        return result;
    }


    // @Override
    public void Viper_init() {
        // Initialize the motor from the hardware map
        viperSlideMotor = hardwareMap.get(DcMotor.class, "Vertical_Viper_Slide");
        // Set motor mode to use encoder
        viperSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Set motor behavior when power is zero (e.g., brake)
        viperSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void Viper_brake() {
        // Initialize the motor from the hardware map and set power to 0 to stop motor
        viperSlideMotor = hardwareMap.get(DcMotor.class, "Vertical_Viper_Slide");
        viperSlideMotor.setPower(0);
        // Set motor behavior when power is zero (e.g., brake)
        viperSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    //   @Override
    public void Viper_loop() {
        // Control slide movement with game pad buttons
        Current_pos = viperSlideMotor.getCurrentPosition();
        //Print where we are in control hub
        telemetry.addData("inside Viper Loop",Current_pos);
        telemetry.update();


        if (gamepad1.dpad_up) {
            // Move slide to the top position
            Viper_moveToPosition(SLIDE_TOP_POSITION);
            if (!viperSlideMotor.isBusy()) {
                viperSlideMotor.setPower(0);
                viperSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        } else if (gamepad1.dpad_down) {
            // Move slide to the bottom position
            Viper_moveToPosition(SLIDE_BOTTOM_POSITION);
        } else if (gamepad1.dpad_left) {
            // Move slide to the mid position
            Viper_moveToPosition(SLIDE_MID_POSITION);
        }
    }


    public void Viper_moveToPosition(int targetPosition) {
        // Set target position and switch to RUN_TO_POSITION mode
        // Apply power to move the motor to the target
        // Set a target position for the slide


        viperSlideMotor = hardwareMap.get(DcMotor.class, "Vertical_Viper_Slide");
        Current_pos = viperSlideMotor.getCurrentPosition();
        telemetry.addData(" UP Viper position:", Current_pos);
        telemetry.update();


        if (gamepad1.dpad_up) {
            viperSlideMotor.setPower(.5);
            viperSlideMotor.setTargetPosition(1100);
            viperSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.dpad_down) {
            viperSlideMotor.setPower(.5);
            viperSlideMotor.setTargetPosition(-1100);
            viperSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (!viperSlideMotor.isBusy()) {


            viperSlideMotor.setPower(0);
            viperSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else if (gamepad1.dpad_left) {
            Viper_brake();
        }
    }


    public LLResult getLatestRobotPosition() {
        telemetry.addData("I am inside the method",null);
        telemetry.update();
        limelight = hardwareMap.get(Limelight3A.class,"Camera");
        limelight.start();
        limelight.pipelineSwitch(0);
        LLResult result = limelight.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose();
                telemetry.addData("Id:",result.getBotpose());
                telemetry.addData("tx", result.getTx());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("Botpose", botpose.toString());
                return result;
            }
        }  else return (null);
        return (null);
    }


}
