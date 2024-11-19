package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Viper Slide Control", group = "teleOp")
public class ViperSlide extends OpMode {


    private DcMotor viperSlideMotor;

    // Define target positions for the slide (in encoder counts)
    private static final int SLIDE_BOTTOM_POSITION = 0;
    private static final int SLIDE_MID_POSITION = 1000;  // adjust based on your setup
    private static final int SLIDE_TOP_POSITION = 2000;  // adjust based on your setup

    public ViperSlide (double v, int i) {
    }

    @Override
    public void init() {
        // Initialize the motor from the hardware map
        viperSlideMotor = hardwareMap.get(DcMotor.class, "viperSlideMotor");

        // Set motor mode to use encoder
        viperSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor behavior when power is zero (e.g., brake)
        viperSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        // Control slide movement with gamepad buttons
        if (gamepad1.dpad_up) {
            // Move slide to the top position
            moveToPosition(SLIDE_TOP_POSITION);
        } else if (gamepad1.dpad_down) {
            // Move slide to the bottom position
            moveToPosition(SLIDE_BOTTOM_POSITION);
        } else if (gamepad1.dpad_left) {
            // Move slide to the mid position
            moveToPosition(SLIDE_MID_POSITION);
        }
    }

    private void moveToPosition(int targetPosition) {
        // Set target position and switch to RUN_TO_POSITION mode
        viperSlideMotor.setTargetPosition(targetPosition);
        viperSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Apply power to move the motor to the target
        viperSlideMotor.setPower(0.6);

        // When the motor reaches its target, stop the motor
        if (!viperSlideMotor.isBusy()) {
            viperSlideMotor.setPower(0);
            viperSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
