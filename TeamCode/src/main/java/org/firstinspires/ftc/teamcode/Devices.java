package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;


import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;




public class Devices {
    DcMotorEx LeftBack;
    DcMotorEx LeftFront;
    DcMotorEx RightBack;
    DcMotorEx RightFront;
    DcMotorEx ViperSlide;
    Limelight3A limelight;


    public void init(HardwareMap Hwmap) {
        // telemetry.addData("I am inside the Devices Init method",null);
        // telemetry.update();
        LeftBack = Hwmap.get(DcMotorEx.class, "Left_Back");
        LeftFront = Hwmap.get(DcMotorEx.class, "Left_Front");


        RightBack = Hwmap.get(DcMotorEx.class, "Right_Back");
        RightFront = Hwmap.get(DcMotorEx.class, "Right_Front");


        ViperSlide = Hwmap.get(DcMotorEx.class, "Vertical_Viper_Slide");


        LeftBack.setDirection(DcMotorEx.Direction.FORWARD);
        RightFront.setDirection(DcMotorEx.Direction.REVERSE);
        LeftFront.setDirection(DcMotorEx.Direction.FORWARD);
        RightBack.setDirection(DcMotorEx.Direction.REVERSE);


        LeftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LeftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


        ViperSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ViperSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        ViperSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        limelight = Hwmap.get(Limelight3A.class, "Camera");
        limelight.start();
        limelight.pipelineSwitch(0);


    }


}





