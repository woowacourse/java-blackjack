package controller;

import domain.Game;
import domain.Player;
import java.util.List;
import view.Answer;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Game game = startGame();
        outputView.displayInitialDeal(game);
        giveAdditionalCards(game);
    }

    public Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames);
    }

    private void giveAdditionalCards(Game game) {
        game.getPlayers()
                .forEach((player) -> hitOrStay(game, player));
    }

    private void hitOrStay(Game game, Player player) {
        Answer answer;
        do {
            if (player.isBlackJack() || player.isBust()) {
                return;
            }
            answer = inputView.readHitOrStay(player);
            if (answer == Answer.YES) {
                game.hit(player);
                outputView.displayPlayerAndCards(player);
            }
        } while (answer == Answer.YES);
    }
}
