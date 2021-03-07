package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Game game = new Game(InputView.receivePlayerNames());

        initCards(game);
        drawCards(game);
        printResults(game);
    }

    private void initCards(Game game) {
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
    }

    private void drawCards(Game game) {
        while (game.hasHitPlayer()) {
            addNewCard(game, game.bringHitPlayer());
        }
        OutputView.printDealerDraw(game.giveCardToDealer());
    }

    private void addNewCard(Game game, User player) {
        if (InputView.askIfMoreCard(player)) {
            game.giveCardToPlayer(player);
            OutputView.printPlayerCard(player);
            return;
        }
         player.convertToStay();
    }

    private void printResults(Game game) {
        OutputView.printUserResult(game.getResultDTOs());
        OutputView.printWinningResult(game.getWinningResultDTOs());
    }
}

