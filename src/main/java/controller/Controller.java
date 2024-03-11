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
import view.card.CardView;

public class Controller {
    private final CardView cardView;
    private final ResultView resultView;

    public Controller(CardView cardView, ResultView resultView) {
        this.cardView = cardView;
        this.resultView = resultView;
    }

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        cardView.printStartStatus(users);
        run(game);
        showGameResult(users, game);
    }

    private void run(Game game) {
        while (game.continueInput()) {
            hitOrStayOnce(game);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            cardView.printDealerHitMessage();
        }
    }

    private void hitOrStayOnce(Game game) {
        User currentPlayer = game.getCurrentPlayer();
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(currentPlayer.getName())
        );
        switch (game.hitOrStay(command)) {
            case HIT -> cardView.printPlayerAndDeck(currentPlayer);
            case BUST -> cardView.printBust(currentPlayer);
        }
    }

    private void showGameResult(Users users, Game game) {
        cardView.printCardsAndSum(users);
        resultView.printResult(game);
    }
}
