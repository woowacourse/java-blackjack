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
        run(users, game);
        showGameResult(users, game);
    }

    private void run(Users users, Game game) {
        for (User user : users.getPlayers()) {
            hitOrStay(user, game);
        }
        while (users.isDealerCardAddable()) {
            game.addDealerCard();
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(User user, Game game) {
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(user.getNameValue())
        );
        switch (game.hitOrStay(command, user)) {
            case HIT -> hitOrStayAgain(user, game);
            case BUST -> OutputView.printBust(user);
        }
    }

    private void hitOrStayAgain(User user, Game game) {
        OutputView.printPlayerAndVisibleCards(user);
        hitOrStay(user, game);
    }

    private void showGameResult(Users users, Game game) {
        OutputView.printAllUserCardsAndSum(users);
        OutputView.printFinalResult(game);
    }
}
