package controller;

import static view.Command.YES;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.game.GameResult;
import domain.game.PlayerResults;
import domain.user.Player;
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
        Deck deck = new Deck(new DeckGenerator().generate());
        Users users = ExceptionHandler.handle(InputView::inputNames);
        users.putStartCards(deck);
        OutputView.printStartStatus(users);
        run(users, deck);
        showGameResult(users);
    }

    private void run(Users users, Deck deck) {
        for (Player player : users.getPlayers()) {
            hitOrStay(player, deck);
        }
        while (users.isDealerCardAddable()) {
            users.addDealerCard(deck.getNewCard());
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(Player player, Deck deck) {
        while (YES == inputValidatedCommand(player.getNameValue()) && !player.busted()) {
            player.addCard(deck.getNewCard());
            printByState(player);
        }
    }

    private static Command inputValidatedCommand(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputAddCommand(nameValue));
    }

    private void printByState(Player player) {
        OutputView.printUserAndCards(player.getNameValue(), player.getAllCards());
        if (player.busted()) {
            OutputView.printBust();
        }
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
