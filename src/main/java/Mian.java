import controlers.UserControler;

import java.util.Scanner;

public class Mian {
    public static void main(String[] args) {

        UserControler userControler = new UserControler();
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
            if (userControler.isLoginExist(login)) {
                userControler.createUser(login, password);
                isWorking = false;
            }
            if(isWorking){
            System.out.println("ten login jest zajety!");
            }
        } while (isWorking);

        /**LOGOWANIE**/ // zmienic w userControl i dokonczyc
//        do {
//            System.out.println("Podaj login: ");
//            login = scanner.nextLine();
//            System.out.println("Podaj haslo: ");
//            password = scanner.nextLine();
//            if (userControler.isLoginExist(login)) {
//                userControler.createUser(login, password);
//                isWorking = false;
//            }
//        } while (isWorking);
    }
}