package controller;

import domain.Command;
import domain.ExceptionHandler;
import domain.card.Card;
import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import domain.deck.UserDeck;
import domain.game.Game;
import domain.game.Index;
import domain.game.State;
import domain.user.Name;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.game.State.BUST;
import static domain.game.State.HIT;

public class Controller {

    public void play() {
        Users users = ExceptionHandler.handle(InputView::inputNames);
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        showStartStatus(users);

        hitOrStay(game);
        showGameResult(users, game);
    }

    private void showStartStatus(Users users) {
        List<Name> names = getNameOfUsers(users);
        Name dealerName = users.getDealer().getName();
        Card dealerCard = users.getDealer().getVisibleCard();
        Map<Name, List<Card>> userCards = getCardOfUsers(users);
        ResultView.showStartStatus(names, dealerName, dealerCard, userCards);
    }

    private List<Name> getNameOfUsers(Users users) {
        return users.getPlayers()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    private Map<Name, List<Card>> getCardOfUsers(Users users) {
        return users.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        User::getName,
                        user -> user.getUserDeck().getCards(),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
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
        return getIndex(state, index, userName, userDeck.getCards());
    }

    private Index getIndex(State state, Index index, Name name, List<Card> userDeck) {
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
        ResultView.showResult(game.generatePlayerResults(), game.generateDealerResult());
    }
}
