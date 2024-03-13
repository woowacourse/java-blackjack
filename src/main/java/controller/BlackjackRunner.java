package controller;

import static view.Command.YES;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.money.Money;
import domain.money.PlayersMoney;
import domain.user.Dealer;
import domain.user.GameUsers;
import domain.user.Player;
import domain.user.Players;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import view.Command;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    public void play() {
        Deck deck = new Deck(new DeckGenerator().generate());
        Players players = ExceptionHandler.handle(InputView::inputPlayers);
        PlayersMoney playersMoney = new PlayersMoney(inputPlayersMoney(players));
        GameUsers gameUsers = new GameUsers(players, deck);
        OutputView.printStartStatus(gameUsers);
        doPlayersTurn(players, deck);
        doDealersTurn(gameUsers.getDealer(), deck);
        doResult(playersMoney, gameUsers);
    }

    private Map<Player, Money> inputPlayersMoney(Players players) {
        return players.getPlayers()
                .stream().collect(Collectors.toMap(
                        Function.identity(),
                        player -> getValidatedMoney(player.getNameValue())
                ));
    }

    private Money getValidatedMoney(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputMoney(nameValue));
    }

    private void doPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitOrStay(player, deck);
        }
    }

    private void doDealersTurn(Dealer dealer, Deck deck) {
        while (dealer.isAddable()) {
            dealer.addCard(deck.getNewCard());
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(Player player, Deck deck) {
        if (player.isBlackJack()) {
            OutputView.printBlackjack(player.getNameValue());
            return;
        }
        while (!player.busted() && YES == inputValidatedCommand(player.getNameValue())) {
            player.addCard(deck.getNewCard());
            printByState(player);
        }
    }

    private Command inputValidatedCommand(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputAddCommand(nameValue));
    }

    private void printByState(Player player) {
        OutputView.printUserAndCards(player.getNameValue(), player.getAllCards());
        if (player.busted()) {
            OutputView.printBust();
        }
    }

    private void doResult(PlayersMoney playersMoney, GameUsers gameUsers) {
        playersMoney.changeIfBlackjack();
        playersMoney.changeByPlayerResults(gameUsers.generatePlayerResults());
        printGameResult(gameUsers, playersMoney);
    }

    private void printGameResult(GameUsers gameUsers, PlayersMoney playersMoney) {
        OutputView.printAllUserCardsAndSum(gameUsers);
        OutputView.printFinalResult(playersMoney);
    }
}
