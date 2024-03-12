package controller;

import static domain.game.State.BUSTED;
import static domain.game.State.HITTABLE;
import static view.Command.YES;

import domain.ExceptionHandler;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.game.GameResult;
import domain.game.PlayerResults;
import domain.game.State;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import view.Command;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    public void play() {
        TotalDeck totalDeck = new TotalDeck(new TotalDeckGenerator().generate());
        Users users = ExceptionHandler.handle(InputView::inputNames);
        users.setStartCards(totalDeck);
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
        boolean addable = true;
        while (addable) {
            addable = isAddable(user, totalDeck);
        }
    }

    private boolean isAddable(User user, TotalDeck totalDeck) {
        Command command = ExceptionHandler.handle(
                () -> InputView.inputAddCommand(user.getNameValue())
        );
        if (YES == command) {
            hit(user, totalDeck);
            printByState(user);
            return user.isAddable();
        }
        return false;
    }

    private void hit(User user, TotalDeck totalDeck) {
        user.addCard(totalDeck.getNewCard());
    }

    private void printByState(User user) {
        switch (hitResultState(user)) {
            case HITTABLE -> OutputView.printPlayerAndVisibleCards(user);
            case BUSTED -> OutputView.printBust(user);
        }
    }

    private State hitResultState(User user) {
        if (user.busted()) {
            return BUSTED;
        }
        return HITTABLE;
    }

    private void showGameResult(Users users) {
        OutputView.printAllUserCardsAndSum(users);
        OutputView.printFinalResult(generatePlayerResults(users));
    }

    private PlayerResults generatePlayerResults(Users users) {
        Map<Player, GameResult> playerResults = users.getPlayers()
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
