package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import domain.deck.UserDeck;
import domain.game.Game;
import domain.game.Index;
import domain.game.State;
import domain.user.Name;
import domain.user.Users;
import view.InputView;
import view.ResultView;

import static domain.game.State.BUST;
import static domain.game.State.HIT;

public class Controller {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        ResultView.showStartStatus(users);
        hitOrStay(game);
        showGameResult(users, game);
    }

    private void hitOrStay(Game game) {
        Index index = game.giveIndexOfGame();
        while (index.isEnd()) {
            index = hitOrStayOnce(game, index);
        }
        while (game.isDealerCardAddCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private Index hitOrStayOnce(Game game, final Index index) {
        Name userName = game.getNameByIndex(index);
        UserDeck userDeck = game.getUserDeckByIndex(index);
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(userName)
        );
        State state = game.determineState(command, index);
        return getIndex(state, index, userName, userDeck);
    }

    private Index getIndex(State state, Index index, Name name, UserDeck userDeck) {
        if (state == HIT) {
            ResultView.printPlayerAndDeck(name, userDeck);
            return index;
        }
        if (state == BUST) {
            ResultView.printBust(name, userDeck);
            return index.next();
        }
        return index.next();
    }

    private void showGameResult(Users users, Game game) {
        ResultView.showCardsAndSum(users);
        ResultView.showResult(game);
    }
}
