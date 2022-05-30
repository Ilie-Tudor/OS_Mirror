package Application;
import java.util.Random;
abstract public class BaseApplication {
    protected int id;
    protected String name;
    protected String url;
    protected long installerSize = 0;

    BaseApplication(String name, String url){
        Random rand = new Random();
        this.id = rand.nextInt(9999999);
        this.name = name;
        this.url = url;
    }
    BaseApplication(int id, String name, String url, long installerSize){
        this.id = id;
        this.name = name;
        this.url = url;
        this.installerSize=installerSize;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getUrl(){
        return this.url;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUrl(String url){
        this.url = url;
    }
    protected void setInstallerSize(){
        this.installerSize = 20;
    }
    public long getInstallerSize(){
        return this.installerSize;
    }
    abstract public void displayInfo();
    abstract public void installApp();
}
