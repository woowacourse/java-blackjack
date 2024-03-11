package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.game.Game;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.OutputView;

public class Controller {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        OutputView.printStartStatus(users);
        run(game);
        showGameResult(users, game);
    }

    private void run(Game game) {
        while (game.continueInput()) {
            hitOrStayOnce(game);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStayOnce(Game game) {
        User currentPlayer = game.getCurrentPlayer();
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(currentPlayer.getNameValue())
        );
        switch (game.hitOrStay(command)) {
            case HIT -> OutputView.printPlayerAndVisibleCards(currentPlayer);
            case BUST -> OutputView.printBust(currentPlayer);
        }
    }

    private void showGameResult(Users users, Game game) {
        OutputView.printAllUserCardsAndSum(users);
        OutputView.printFinalResult(game);
    }
}
