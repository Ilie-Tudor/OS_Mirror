package Menu;


import Application.BaseApplication;
import Handlers.*;
import Stores.DBStore;
import Stores.FileStore;
import Stores.MemoryStore;

import java.util.Scanner;

public class Menu {


    private static Menu menu_instance = null;



    FileStore fs = new FileStore();
    DBStore dbs = new DBStore();
    int option = 0;
    ApplicationHandlerInterface handler;
    MemoryStore ms = new MemoryStore();

    static public Menu get_instance(){
        if (menu_instance == null){
            menu_instance = new Menu();
        }
        return menu_instance;
    }

    private Menu(){
        ms = dbs.importAllIntoMemory();
        dbs.exportAllFromMemory(ms);
        handler = new ApplicationsHandler(ms);
    }

    public void displayOptions(){
        System.out.println("Select one of the options and hit enter");
        System.out.println("1. Display all available applications");
        System.out.println("2. List application info");
        System.out.println("3. Add a new application");
        System.out.println("4. Update an application");
        System.out.println("5. Delete an application");
        System.out.println("6. Install an application");
        System.out.println("7. Save changes");
        System.out.println("8. Exit");
        System.out.print("Your option: ");
    }
    public void selectOption(){
        Scanner in = new Scanner(System.in);
        option = in.nextInt();
    }
    private boolean isNumber(String s) {
        boolean isNumber = true;
        for (char c : s.toCharArray()) {
            isNumber = isNumber && Character.isDigit(c);
        }
        return isNumber;
    }
    public void handleOption(){
        switch (option){
            case 1:
                handler.displayAllAppsInfo();
                break;
            case 2:
                System.out.print("Input an application id or name: ");
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                if(isNumber(input)) {
                    handler.displayAppInfoById(Integer.parseInt(input));
                }
                else{
                    handler.displayAppInfoById(handler.getIdByName(input));
                }
                break;
            case 3:
                System.out.print("Input the name of the application: ");
                in = new Scanner(System.in);
                String name = in.nextLine();
                System.out.print("Input the url of the application installer: ");
                String url = in.nextLine();
                handler.insertApp(name,url);
                break;
            case 4:
                in = new Scanner(System.in);
                System.out.print("Input an application id or name: ");
                String oldApp = in.nextLine();
                System.out.print("Input the new name (if it should be the same leave blank): ");
                String newName = in.nextLine();
                System.out.print("Input the new url (if it should be the same leave blank): ");
                String newUrl = in.nextLine();
                if(isNumber(oldApp)) {
                    if(!(newName==null || newName.equals("")))
                        handler.updateAppName(Integer.parseInt(oldApp),newName);
                    if(!(newUrl==null || newUrl.equals("")))
                        handler.updateAppURL(Integer.parseInt(oldApp),newUrl);
                }
                else{
                    if(!(newName==null || newName.equals("")))
                        handler.updateAppName(handler.getIdByName(oldApp),newName);
                    if(!(newUrl==null || newUrl.equals("")))
                        handler.updateAppURL(handler.getIdByName(oldApp),newUrl);
                }
                break;
            case 5:
                System.out.print("Input an application id or name: ");
                in = new Scanner(System.in);
                input = in.nextLine();
                if(isNumber(input)) {
                    handler.deleteApp(Integer.parseInt(input));
                }
                else{
                    handler.deleteApp(handler.getIdByName(input));
                }
                break;
            case 6:
                System.out.print("Input an application id or name: ");
                in = new Scanner(System.in);
                input = in.nextLine();
                if(isNumber(input)) {
                    handler.installAppById(Integer.parseInt(input));
                }
                else{
                    handler.installAppById(handler.getIdByName(input));
                }
                break;
            case 7:
                dbs.exportAllFromMemory(ms);
                fs.exportAllFromMemory(ms);
                break;
            case 8:
                System.exit(1);
                break;
        }
    }




}
