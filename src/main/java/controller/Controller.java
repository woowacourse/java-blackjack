package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import domain.game.Game;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;

public class Controller {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        ResultView.showStartStatus(users);
        run(game);
        showGameResult(users, game);
    }

    private void run(Game game) {
        while (game.continueInput()) {
            hitOrStayOnce(game);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private void hitOrStayOnce(Game game) {
        User currentPlayer = game.getCurrentPlayer();
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(currentPlayer.getName())
        );
        switch (game.hitOrStay(command)) {
            case HIT -> ResultView.printPlayerAndDeck(currentPlayer);
            case BUST -> ResultView.printBust(currentPlayer);
        }
    }

    private void showGameResult(Users users, Game game) {
        ResultView.showCardsAndSum(users);
        ResultView.showResult(game);
    }
}
