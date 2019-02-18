package com.wowwee.chip_android_sampleproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobot;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobotFinder;
import com.wowwee.chip_android_sampleproject.AudioAnalyzer.AnalyzerActivity;
import com.wowwee.chip_android_sampleproject.AudioAnalyzer.SamplingLoop;
import com.wowwee.chip_android_sampleproject.R;
import com.wowwee.chip_android_sampleproject.utils.FragmentHelper;

import java.util.Calendar;

public class LearningFragment extends Fragment implements ChipRobot.ChipRobotInterface {

    SamplingLoop samplingLoop = null;
    Handler handler;
    public TextView testText;
    public Button toMenu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
            return null;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        handler = new Handler();

        final ChipRobot robot = ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
        robot.setCallbackInterface(this);

        toMenu = (Button)view.findViewById(R.id.toMenu);
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new MenuFragment(), R.id.view_id_content, false);
            }
        });

        return view;
    }


    @Override
    public void chipDeviceReady(ChipRobot chipRobot) {

    }

    @Override
    public void chipDeviceDisconnected(ChipRobot chipRobot) {

    }

    @Override
    public void chipDidReceiveVolumeLevel(ChipRobot chipRobot, byte b) {

    }

    @Override
    public void chipDidReceivedBatteryLevel(ChipRobot chipRobot, float v, byte b, byte b1) {

    }

    @Override
    public void chipDidReceiveDogVersionWithBodyHardware(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {

    }

    @Override
    public void chipDidSendAdultOrKidSpeed() {

    }

    @Override
    public void chipDidReceiveAdultOrKidSpeed(byte b) {

    }

    @Override
    public void chipDidReceiveEyeRGBBrightness(byte b) {

    }

    @Override
    public void chipDidReceiveCurrentClock(int i, int i1, int i2, int i3, int i4, int i5, int i6) {

    }

    @Override
    public void chipDidReceiveCurrentAlarm(int i, int i1, int i2, int i3, int i4) {

    }

    @Override
    public void chipDidReceiveBodyconStatus(int i) {

    }
}
