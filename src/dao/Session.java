package dao;

public class Session {
    public static String currentAccount;

    public static void login(String account){
        currentAccount=account;
    }
    public static void logout(){
        currentAccount=null;
    }
    public static boolean isLoggedIn(){
        return currentAccount != null;
    }
}
