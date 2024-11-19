package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.limelightvision.Limelight3A;


@TeleOp(name="TeleOp", group="Drive")


public class TeleOpV1 extends DriveMethods {


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
        Robot.init(hardwareMap);
    }


    @Override
    public void loop() {
        telemetry.update();
        double leftStickY = square(gamepad1.left_stick_y);
        double leftStickX = square(-gamepad1.left_stick_x);
        double rightStickX = square(-gamepad1.right_stick_x);


        if (gamepad1.dpad_up) {
            Viper_init();
            Viper_moveToPosition(-1000);
        }
        if (gamepad1.dpad_down) {
            Viper_init();
            Viper_moveToPosition(1000);
        }
        if (gamepad1.dpad_left) {
            Viper_brake();
        } else {
            OmniDrive(leftStickY, leftStickX, rightStickX, 0.75);
        }


    }


}
