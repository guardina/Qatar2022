package com.example.fifaqatar2022.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimer {

    final String deadline = "20/11/2022 16:45:00";

    static MyTimer timer;

    static public MyTimer getTimer() {
        if (timer == null) {
            timer = new MyTimer();
            return timer;
        }
        return timer;
    }

    public boolean tooLate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        return deadline.compareTo(formatter.format(date)) < 0;
    }
}
