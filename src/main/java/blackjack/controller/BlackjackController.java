package blackjack.controller;

import blackjack.model.Player;
import blackjack.util.PlayerParser;
import blackjack.view.InputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        String input = InputView.readPlayerName();
        List<Player> players = PlayerParser.parse(input);
    }
}
