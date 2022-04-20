package Application;

public class UserDefinedApplication extends BaseApplication{


//    public void setId(int id){
//        this.id = id;
//    }
    public UserDefinedApplication(String name, String url){
        super(name, url);
    }
    public void displayInfo(){
        System.out.println("-----");
        System.out.println("----- Application: "+this.name+" (type -> user defined)");
        System.out.println("----- URL: "+this.url);
        System.out.println("----- Installer Size: "+this.installerSize);
        System.out.println("----- ID: "+this.id);
        System.out.println("-----");
        System.out.println("");

    }
    public void installApp(){
        System.out.println("----- Installing "+this.name+"...");
        System.out.println("----- Downloading the installer");
        System.out.println("----- Saving download size");
        setInstallerSize();
        System.out.println("----- Running the installer");
        System.out.println("");

    }

}
