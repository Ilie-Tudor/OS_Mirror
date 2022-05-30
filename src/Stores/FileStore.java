package Stores;

import Application.BaseApplication;
import Application.UserDefinedApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileStore implements BaseStore{


    @Override
    public void exportAllFromMemory(MemoryStore ms) {
//        applications save
        try {
            FileWriter applications_file = new FileWriter("./Reports/applications.txt");
            applications_file.write("id, name, url, installer byte size\n");
            Vector<BaseApplication> A = ms.getApplications();
            for(int i=0;i<A.size();i++){
                String line = ""+A.get(i).getId()+","+A.get(i).getName()+","+A.get(i).getUrl()+","+A.get(i).getInstallerSize()+"\n";
                applications_file.write(line);
            }
            applications_file.close();
            System.out.println("saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public MemoryStore importAllIntoMemory() {
//        applications load
        MemoryStore ms = new MemoryStore();
        try {
            File applications_file = new File("./Reports/applications.txt");
            Scanner reader = new Scanner(applications_file);
            int index=-1;
            Vector<BaseApplication> apps = new Vector<>();
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if(index!=-1){
                    String [] str = line.split(",");
                    apps.add(new UserDefinedApplication(Integer.parseInt(str[0]),str[1],str[2],Long.parseLong(str[3])));
                }
                index++;
            }
            ms.setApplications(apps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ms;

    }
}
