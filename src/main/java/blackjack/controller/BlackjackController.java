package blackjack.controller;

import blackjack.view.InputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> playerNames = InputView.inputPlayerName();
        System.out.println(playerNames);
        String hitAnswer = InputView.inputPlayerHit("가나다라");
        System.out.println(hitAnswer);
    }
}
