package view;

import domain.Player;
import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private static final String DRAW_YES = "y";
    private static final String DRAW_NO = "n";
    private final Scanner scanner = new Scanner(System.in);

    public String getParticipant() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public String getMoreCards(Player player) {
        while (true) {
            System.out.println(player.getName() + IOMessage.ASK_MORE_CARD.message());
            String input = scanner.nextLine().trim().toLowerCase();
            if (isValidDrawChoice(input)) {
                return input;
            }
            System.out.println(IOMessage.INVALID_DRAW_CHOICE.message());
        }
    }

    private boolean isValidDrawChoice(String input) {
        return DRAW_YES.equals(input) || DRAW_NO.equals(input);
    }
}
