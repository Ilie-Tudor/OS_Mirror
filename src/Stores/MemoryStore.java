package Stores;

import Application.BaseApplication;

import java.util.Vector;

public class MemoryStore {
    Vector<BaseApplication> Applications = new Vector<>();

    public Vector<BaseApplication> getApplications() {
        return Applications;
    }

    public void setApplications(Vector<BaseApplication> applications) {
        Applications = applications;
    }
}
