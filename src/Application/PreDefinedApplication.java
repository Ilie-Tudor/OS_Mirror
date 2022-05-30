package Application;

import java.io.File;
import java.io.IOException;

public class PreDefinedApplication extends BaseApplication{


    public PreDefinedApplication(int id, String name, String url, long installerSize){super(id,name, url,installerSize);}

    @Override
    public void displayInfo(){
        System.out.println("-----");
        System.out.println("----- Application: "+this.name+" (type -> pre defined)");
        System.out.println("----- URL: "+this.url);
        System.out.println("----- Installer Size: "+this.installerSize);
        System.out.println("----- ID: "+this.id);
        System.out.println("-----");
        System.out.println("");

    }

    @Override
    public void installApp(){


        try{
            File dir = new File("./Installers/"+this.name+this.id);
            if(!dir.exists()) {
//            System.out.println("nu exista");
                dir.mkdir();
            }
            String download_command = "curl -O -J --output-dir ./Installers/"+this.name+this.id+" "+this.url;
            Process p_d = Runtime.getRuntime().exec(download_command);
            while(p_d.isAlive()){}
            var pathname = dir.list()[0];
            this.installerSize = (new File("./Installers/"+this.name+this.id+"/"+pathname)).length();
//        System.out.println(pathname);
            String install_command = "cmd.exe /C \"start ./Installers/"+this.name+this.id+"/"+pathname+"\"";
//        System.out.println(install_command);
            Process p_i = Runtime.getRuntime().exec(install_command);
//        while(p_i.isAlive()){}
            System.out.println("App installer started... go and click next :)))");

        }catch (IOException e){
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
//        System.exit(-1);
        }




    }
}
