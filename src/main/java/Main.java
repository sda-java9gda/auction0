import controllers.UserControler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserControler userController = new UserControler();
//<<<<<<< HEAD
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        boolean isWorking = true;

        /**REJESTRACJA KONTA**/
        do {
            System.out.println("Podaj login: ");
            login = scanner.nextLine();
            System.out.println("Podaj haslo: ");
            password = scanner.nextLine();
            if (userController.isLoginExist(login)) {
                userController.createUser(login, password); // zmienic na wyjatek + try catch
                isWorking = false;
            }
            if(isWorking){
            System.out.println("ten login jest zajety!");
            }
        } while (isWorking);
//=======
//
//>>>>>>> 2f92c40c137a77cdc54e7522ea8b7164b4fff244

        /**LOGOWANIE**/ // zmienic w userControl i dokonczyc
//        do {
//            System.out.println("Podaj login: ");
//            login = scanner.nextLine();
//            System.out.println("Podaj haslo: ");
//            password = scanner.nextLine();
//            if (userController.isLoginExist(login)) {
//                userController.createUser(login, password);
//                isWorking = false;
//            }
//        } while (isWorking);
    }
}