import controllers.UserControler;
import org.omg.CORBA.UShortSeqHelper;

import java.util.Scanner;

public class Main {

    public enum State {
        INIT,

        LOGGING,
        CREATING,

        LOGGED,

        AUCTION_CREATE,
        AUCTION_LIST,
        AUCTION_OFFER,

        SAVE_DATA,
        EXIT,
    }

    public static void main(String[] args) {
        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);

        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    System.out.println("Witaj w sklepie, co chcesz zrobic:");
                    System.out.println("1 - zaloguj sie ");
                    System.out.println("2 - stworz konto");
                    System.out.println("3 - zobacz aukcje");
                    System.out.println("4 - wystaw aukcje");
                    System.out.println("0 - wyloguj ");

                    String answer = scanner.nextLine();

                    switch (answer) {
                        case ("1"):
                            state = State.LOGGING;
                            break;
                        case ("2"):
                            state = State.CREATING;
                            break;
                        case ("0"):
                            state = State.EXIT;
                            break;
                        default:
                            System.out.println("nieprawidlowy znak");
                            state = State.INIT;
                            break;
                    }
                    break;
                }
                case LOGGING:
                    String login, pasword;
                    boolean loginSucces;

                    System.out.println("podaj login");
                    login = scanner.nextLine();

                    System.out.println("Podaj haslo");
                    pasword = scanner.nextLine();

                    loginSucces = UserControler.loginUser(login, pasword);
                    if (loginSucces) {
                        state = State.LOGGED;
                    } else {
                        state = State.INIT;
                    }
                    break;
                case CREATING:
                    System.out.println("podaj login");
                    login = scanner.nextLine();

                    System.out.println("Podaj haslo");
                    pasword = scanner.nextLine();

                    loginSucces = UserControler.addUser(login, pasword);
                    if (loginSucces) {
                        state = State.CREATING;
                    } else {
                        state = State.INIT;
                    }
                    break;
                case LOGGED:
                    System.out.println("Wybierz opcje:");
                    System.out.println(" 1 - wyswietl liste aukcji");
                    System.out.println(" 2 - dodaj aukcje");
                    System.out.println(" 3 - usun aukcje");
                    System.out.println(" 0 - wyloguj sie");
            }
        }

        UserControler userController = new UserControler();

        String login, password;
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
            if (isWorking) {
                System.out.println("ten login jest zajety!");
            }
        } while (isWorking);


        /**LOGOWANIE**/ // zmienic w userControl i dokonczyc
        do {
            System.out.println("Podaj login: ");
            login = scanner.nextLine();
            System.out.println("Podaj haslo: ");
            password = scanner.nextLine();
            if (userController.isLoginExist(login)) {
                userController.createUser(login, password);
                isWorking = false;
            }
        } while (isWorking);
    }
}