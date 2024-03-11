package controller;

import static domain.Command.YES;
import static domain.game.State.BUST;
import static domain.game.State.HIT;

import domain.Command;
import domain.ExceptionHandler;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.game.PlayerResults;
import domain.game.Result;
import domain.game.State;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {

    public void play() {
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = ExceptionHandler.handle(InputView::inputNames);
        OutputView.printStartStatus(users);
        run(users, totalDeck);
        showGameResult(users);
    }

    private void run(Users users, TotalDeck totalDeck) {
        for (User user : users.getPlayers()) {
            hitOrStay(user, totalDeck);
        }
        while (users.isDealerCardAddable()) {
            users.addDealerCard(totalDeck.getNewCard());
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(User user, TotalDeck totalDeck) {
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(user.getNameValue())
        );
        if (YES == command) {
            hit(user, totalDeck);
        }
    }

    private void hit(User user, TotalDeck totalDeck) {
        switch (hitOrBust(user, totalDeck)) {
            case HIT -> hitOrStayAgain(user, totalDeck);
            case BUST -> OutputView.printBust(user);
        }
    }

    private State hitOrBust(User user, TotalDeck totalDeck) {
        user.addCard(totalDeck.getNewCard());
        return hitOrBust(user);
    }

    private State hitOrBust(User user) {
        if (user.busted()) {
            return BUST;
        }
        return HIT;
    }

    private void hitOrStayAgain(User user, TotalDeck totalDeck) {
        OutputView.printPlayerAndVisibleCards(user);
        hitOrStay(user, totalDeck);
    }

    private void showGameResult(Users users) {
        OutputView.printAllUserCardsAndSum(users);
        OutputView.printFinalResult(generatePlayerResults(users));
    }

    private PlayerResults generatePlayerResults(Users users) {
        Map<Player, Result> playerResults = users.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        users::generatePlayerResult,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
        return new PlayerResults(playerResults);
    }
}
