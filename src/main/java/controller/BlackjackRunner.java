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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.Command;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    public void play() {
        Deck deck = new Deck(new DeckGenerator().generate());
        PlayersMoney playersMoney = new PlayersMoney(inputPlayersMoney());
        GameUsers gameUsers = new GameUsers(playersMoney, deck);
        OutputView.printStartStatus(gameUsers);
        doPlayersTurn(gameUsers.getPlayers(), deck);
        doDealersTurn(gameUsers.getDealer(), deck);
        doResult(playersMoney, gameUsers);
    }

    private Map<Player, Money> inputPlayersMoney() {
        return ExceptionHandler.handle(InputView::inputNames)
                .stream()
                .collect(Collectors.toMap(
                        Player::new,
                        name -> getValidatedMoney(name.value())
                ));
    }

    private Money getValidatedMoney(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputMoney(nameValue));
    }

    private void doPlayersTurn(List<Player> players, Deck deck) {
        for (Player player : players) {
            hitOrStay(player, deck);
        }
    }

    private void doDealersTurn(Dealer dealer, Deck deck) {
        while (dealer.isReceivable()) {
            dealer.receiveCard(deck.drawCard());
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(Player player, Deck deck) {
        if (player.isBlackjack()) {
            OutputView.printBlackjack(player.getNameValue());
            return;
        }
        while (player.isReceivable() && YES == inputValidatedCommand(player.getNameValue())) {
            player.receiveCard(deck.drawCard());
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
        PlayersMoney resultPlayersMoney = playersMoney.changeByPlayersResult(gameUsers.generatePlayersResult());
        printGameResult(gameUsers, resultPlayersMoney);
    }

    private void printGameResult(GameUsers gameUsers, PlayersMoney playersMoney) {
        OutputView.printAllUserCardsAndSum(gameUsers);
        OutputView.printFinalResult(playersMoney);
    }
}
