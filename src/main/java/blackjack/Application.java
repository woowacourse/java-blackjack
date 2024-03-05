package blackjack;

import blackjack.domain.Players;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        Players players = Players.from(InputView.readPlayerNames());
    }
}
