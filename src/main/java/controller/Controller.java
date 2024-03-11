package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.game.Game;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;
import view.UserView;

public class Controller {
    private final UserView userView;
    private final ResultView resultView;

    public Controller(UserView userView, ResultView resultView) {
        this.userView = userView;
        this.resultView = resultView;
    }

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        userView.printStartStatus(users);
        run(game);
        showGameResult(users, game);
    }

    private void run(Game game) {
        while (game.continueInput()) {
            hitOrStayOnce(game);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            userView.printDealerHitMessage();
        }
    }

    private void hitOrStayOnce(Game game) {
        User currentPlayer = game.getCurrentPlayer();
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(currentPlayer.getNameValue())
        );
        switch (game.hitOrStay(command)) {
            case HIT -> userView.printPlayerAndVisibleCards(currentPlayer);
            case BUST -> userView.printBust(currentPlayer);
        }
    }

    private void showGameResult(Users users, Game game) {
        userView.printAllUserCardsAndSum(users);
        resultView.printResult(game);
    }
}
