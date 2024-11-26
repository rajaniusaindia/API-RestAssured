package com.ra.postapis;

public class Reqres {
    // create class variables
    String name;
    String job;

    // generate ctr

    public Reqres(String name, String job) {
        this.name = name;
        this.job = job;
    }

    //generate getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
