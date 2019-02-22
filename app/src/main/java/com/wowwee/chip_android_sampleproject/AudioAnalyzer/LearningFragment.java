package com.wowwee.chip_android_sampleproject.AudioAnalyzer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wowwee.bluetoothrobotcontrollib.chip.ChipCommandValues;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobot;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobotFinder;
import com.wowwee.chip_android_sampleproject.R;
import com.wowwee.chip_android_sampleproject.utils.FragmentHelper;



import java.util.Random;


public class LearningFragment extends Fragment /*implements ChipRobot.ChipRobotInterface */{

    SamplingLoop samplingLoop = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*Handler handler;
        public TextView testText;
        public Button toMenu;
        public Button startLearning;*/

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

        ListView listView = (ListView)view.findViewById(R.id.menuTable);
        String[] robotNameArr = {"Back", "Start","Stop"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, robotNameArr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (ChipRobotFinder.getInstance().getChipRobotConnectedList().size() > 0) {
                    ChipRobot robot = (ChipRobot) ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);

                   int start=0;
                   int stop=0;
                    switch (position) {
                        case 0:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new MenuFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            start=1;
                            stop=0;
                            break;
                        case 2:
                            stop=1;
                            start=0;

                    }
                   while(start==1) {
                        samplingLoop.run();
                        if (samplingLoop.holder == 1) {
                            rewardDog();
                        }
                    }
                    while(stop==1) {
                       samplingLoop.finish(); }
                }}});


                return view;
            }

/*
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
                */
            public void rewardDog() {
                if (ChipRobotFinder.getInstance().getChipRobotConnectedList().size() > 0) {
                    ChipRobot robot = (ChipRobot) ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
                    //Play animation 5 = dance
                    //Play sound 110 =  demo music 2
                    //Play sound 111 = demo music 3

                    Random rand = new Random();
                    int randomValue = rand.nextInt(3);
                    if (randomValue == 0) {
                        robot.chipPlayBodycon((byte) (5));
                    } else if (randomValue == 1) {
                        ChipCommandValues.kChipSoundFileValue value = ChipCommandValues.kChipSoundFileValue.kChipSoundFile_None;
                        value.setValue(110);
                        robot.chipPlaySound(value);
                    } else {
                        ChipCommandValues.kChipSoundFileValue value = ChipCommandValues.kChipSoundFileValue.kChipSoundFile_None;
                        value.setValue(111);
                        robot.chipPlaySound(value);
                    }
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Reset dog
                    robot.chipPlayBodycon((byte) (1));
                    robot.chipStopSound();
                }
            }



    }

