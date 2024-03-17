package runner;

import static view.Command.YES;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.money.PlayerProfits;
import domain.money.Profit;
import domain.user.Dealer;
import domain.user.Hand;
import domain.user.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import view.Command;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    public void play() {
        Deck deck = new Deck(new DeckGenerator().generate());
        PlayerProfits playerProfits = new PlayerProfits(inputPlayersMoney(deck));
        Dealer dealer = new Dealer(new Hand(deck.drawCard(), deck.drawCard()));
        OutputView.printStartStatus(dealer.getHand(), playerProfits.getPlayers());
        doPlayersTurn(playerProfits, deck);
        doDealersTurn(dealer, deck);
        doResult(playerProfits, dealer);
    }

    private Map<Player, Profit> inputPlayersMoney(Deck deck) {
        return ExceptionHandler.handle(InputView::inputNames)
                .stream()
                .collect(Collectors.toMap(
                        name -> new Player(name, new Hand(deck.drawCard(), deck.drawCard())),
                        name -> getValidatedMoney(name.value()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private Profit getValidatedMoney(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputMoney(nameValue));
    }

    private void doPlayersTurn(PlayerProfits playerProfits, Deck deck) {
        playerProfits.doForAllPlayers(player -> doPlayerTurn(deck, player));
    }

    private void doPlayerTurn(Deck deck, Player player) {
        while (player.isReceivable() && YES == getValidatedCommand(player.getNameValue())) {
            player.receive(deck.drawCard());
            printByState(player);
        }
        if (player.isBlackjack()) {
            OutputView.printBlackjack(player.getNameValue());
        }
    }

    private void printByState(Player player) {
        OutputView.printUserAndCards(player.getNameValue(), player.getAllCards());
        if (player.isBusted()) {
            OutputView.printBust();
        }
    }

    private void doDealersTurn(Dealer dealer, Deck deck) {
        while (dealer.isReceivable()) {
            dealer.receive(deck.drawCard());
            OutputView.printDealerHitMessage();
        }
    }

    private Command getValidatedCommand(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputAddCommand(nameValue));
    }

    private void doResult(PlayerProfits playerProfits, Dealer dealer) {
        PlayerProfits resultPlayerProfits = playerProfits.generateMoneyResult(dealer);
        printGameResult(dealer, resultPlayerProfits);
    }

    private void printGameResult(Dealer dealer, PlayerProfits playerProfits) {
        OutputView.printAllUserCardsAndSum(dealer.getHand(), playerProfits.getPlayers());
        OutputView.printFinalResult(playerProfits);
    }
}
