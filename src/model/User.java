package model;

public class User {
    private int id;
    private static String name;
    private static String username;
    private static String password;

   public void setName(String name){
       User.name=name;
   }
   public void setUsername(String username){
       User.username=username;
   }
   public void setPassword(String password){
       User.password =password;
   }

   public static String getName(){
       return name;
   }
   public static String getUsername(){
       return username;
   }
   public static String getPassword(){
       return password;
   }

    public void setId(int id) {
       this.id=id;
    }

    public static int getId() {
       return id;
    }
}
