package view;

import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getParticipant() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public String getMoreCards(String name) {
        System.out.println(name + IOMessage.ASK_MORE_CARD.message());
        return scanner.nextLine();
    }
}
