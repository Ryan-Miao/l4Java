package com.test.java.clazz.innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TalkingClock {



    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(int interval2, boolean beep) {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Every "+interval+", At the tone, the time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }

        ActionListener listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }



    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start(1000, false);

        //keep program running until user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
