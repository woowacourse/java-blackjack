package view;

import domain.DrawMore;
import domain.Player;
import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getParticipant() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public boolean getMoreCards(Player player) {
        while (true) {
            System.out.println(player.getName() + IOMessage.ASK_MORE_CARD.message());
            final String input = scanner.nextLine().trim().toLowerCase();

            if(DrawMore.isCorrectAnswer(input)){
                return DrawMore.isYes(input);
            }

            System.out.println(IOMessage.INVALID_DRAW_CHOICE.message());
        }
    }
}
