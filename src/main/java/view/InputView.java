package view;

import domain.HitStand;
import domain.Player;
import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getPlayerNames() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public boolean askHitOrStand(Player player) {
        while (true) {
            System.out.println(player.getName() + IOMessage.ASK_HIT_OR_STAND.message());
            final String input = scanner.nextLine().trim().toLowerCase();

            if(HitStand.validate(input)){
                return HitStand.isHit(input);
            }

            System.out.println(IOMessage.INVALID_HIT_OR_STAND_DECISION.message());
        }
    }
}
