package view;

import domain.Player;
import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getParticipant() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public String getMoreCards(Player player) {
        System.out.println(player.getName() + IOMessage.ASK_MORE_CARD.message());
        return scanner.nextLine();
    }
}
