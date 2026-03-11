package view;

import meesage.InputMessage;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public String askUsersName(){
        System.out.println(InputMessage.ASK_USER_NAME.getMessage());
        return scanner.nextLine();
    }

    public String askAddCard(String name){
        System.out.printf(InputMessage.ASK_ADD_CARD.getMessage(), name);
        System.out.println();
        return scanner.nextLine();
    }
}
