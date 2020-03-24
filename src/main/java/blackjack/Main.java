package blackjack;

import blackjack.controller.Controller;
import blackjack.controller.ModeStrategy;
import blackjack.controller.ModeStrategyFactory;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;

public class Main {

    @SuppressWarnings("unchecked")
    public static <T extends Player> void main(String[] args) {
        String selectedGame = InputView.enterGame();
        ModeStrategy<T> gameMode = ModeStrategyFactory.createByIdentifier(selectedGame);
        Controller.play(gameMode);
    }
}
