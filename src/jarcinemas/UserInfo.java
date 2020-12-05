package jarcinemas;

/**
 *
 * @author asifrasheed
 */
public class UserInfo {
    private static String Name, username;
    private static int type;
    
    public UserInfo(){
        // Does nothing
    }
    public UserInfo(String n, String un, int t){
        Name = n;
        username = un;
        type = t;
    }
    
    public String getName(){
        return Name;
    }
    
    public String username(){
        return username;
    }
    
    public void setName(String n){
        Name = n;
    }
    
    public void setUser(String u){
        username = u;
    }
    
    public boolean isAdmin(){
        return type==0;
    }
    public boolean isPOS(){
        return type==2;
    }
}
