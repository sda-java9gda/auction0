package views;

public class OtherViews {
    public static void welcomeView(){
        System.out.println("Welcome to our store!\n");
    }
    public static void initView(){
        System.out.println("What do you want to do?");
        System.out.println("Press \"1\"  to login");
        System.out.println("Press \"2\"  to register account");
        System.out.println("Press \"3\"  to remove account");
        System.out.println("Press \"0\"  to exit");
    }
    public static void loggedView(){

    }
    public static void invalidCharacter(){
        System.out.println("Invalid character!");
    }
}
