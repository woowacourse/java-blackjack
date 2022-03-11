package blackjack;

import blackjack.domain.HitOrStand;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Main {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.run();
    }
}
