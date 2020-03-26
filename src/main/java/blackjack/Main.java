package blackjack;

import blackjack.controller.Controller;
import blackjack.controller.ModeStrategy;
import blackjack.controller.ModeStrategyFactory;
import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;

public class Main {

    public static void main(String[] args) {
        Player bettingPlayer = new BettingPlayer("쪼밀리", 1000);
        int i = bettingPlayer.computeScore();

        String selectedGame = InputView.enterGame();
        ModeStrategy<?> gameMode = ModeStrategyFactory.createByIdentifier(selectedGame);
        Controller.play(gameMode);
    }
}
