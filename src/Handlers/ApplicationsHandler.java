package Handlers;

import Application.BaseApplication;

import java.util.Vector;
import Application.*;

public class ApplicationsHandler implements ApplicationHandlerInterface {


    Vector<BaseApplication> v = new Vector<>();


    @Override
    public void displayAllAppsInfo() {
        for(int i=0;i<v.size();i++){
            v.get(i).displayInfo();
        }
    }

    @Override
    public void displayAppInfoById(int id) {
        for(int i=0;i<v.size();i++){
            if(v.get(i).getId()==id)
                v.get(i).displayInfo();
        }
    }

    @Override
    public void insertApp(String name, String url) {
        v.add(new UserDefinedApplication(name,url));
    }

    @Override
    public void updateAppName(int id, String name) {
        for(int i=0;i<v.size();i++){
            if(v.get(i).getId()==id)
                v.get(i).setName(name);
        }
    }

    @Override
    public void updateAppURL(int id, String url) {
        for(int i=0;i<v.size();i++){
            if(v.get(i).getId()==id)
                v.get(i).setUrl(url);
        }
    }

    @Override
    public void deleteApp(int id) {
        for(int i=0;i<v.size();i++){
            if(v.get(i).getId()==id)
                v.remove(i);
        }
    }

    @Override
    public void installAppById(int id) {
        for(int i=0;i<v.size();i++){
            if(v.get(i).getId()==id)
                v.get(i).installApp();
        }
    }

    @Override
    public int getIdByName(String name){
        for(int i=0;i<v.size();i++){
            if(v.get(i).getName().equals(name))
                return v.get(i).getId();
        }
        return -1;
    }


}
