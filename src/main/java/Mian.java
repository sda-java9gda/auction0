import controlers.UserControler;

public class Mian {
    public static void main(String[] args) {

        UserControler userControler = new UserControler();

        userControler.createUser("Lukasz","1234");
        userControler.createUser("Tomek","23");
        userControler.createUser("Mateusz","5555");
        userControler.createUser("Asia","11111");


    }
}