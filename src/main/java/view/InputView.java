package view;

import java.util.Scanner;
import message.IoMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getParticipant() {
        System.out.println(IoMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public String getMoreCards(String name) {
        System.out.println(name + IoMessage.ASK_MORE_CARD.message());
        return scanner.nextLine();
    }

    public String getMoney(String name) {
        System.out.println(name + IoMessage.ASK_MONEY.message());
        return scanner.nextLine();
    }
}
