package Handlers;

public interface ApplicationHandlerInterface {

    void displayAllAppsInfo();
    void displayAppInfoById(int id);
    void insertApp(String name, String url);
    void updateAppName(int id, String name);
    void updateAppURL(int id, String url);
    void deleteApp(int id);
    void installAppById(int id);
    int getIdByName(String name);
}
