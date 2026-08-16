package service;

import java.util.Date;

public class Scheduler {


    public void execute() {
        System.out.println("SCHEDULED JOB FIRED AT: " + new Date());
    }
}