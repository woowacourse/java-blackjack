package runner;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.money.Money;
import domain.money.Players;
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
        Players players = new Players(inputPlayersMoney(deck));
        Dealer dealer = new Dealer(new Hand(deck.drawCard(), deck.drawCard()));
        OutputView.printStartStatus(dealer.getHand(), players.keySet());
        players.play(this::getValidatedCommand, deck);
        dealer.play(deck);
        doResult(players, dealer);
    }

    private Map<Player, Money> inputPlayersMoney(Deck deck) {
        return ExceptionHandler.handle(InputView::inputNames)
                .stream()
                .collect(Collectors.toMap(
                        name -> new Player(name, new Hand(deck.drawCard(), deck.drawCard())),
                        name -> getValidatedMoney(name.value()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private Money getValidatedMoney(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputMoney(nameValue));
    }

    private Command getValidatedCommand(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputAddCommand(nameValue));
    }

    private void doResult(Players players, Dealer dealer) {
        Players resultPlayers = players.generateMoneyResult(dealer);
        printGameResult(dealer, resultPlayers);
    }

    private void printGameResult(Dealer dealer, Players players) {
        OutputView.printAllUserCardsAndSum(dealer.getHand(), players.keySet());
        OutputView.printFinalResult(players);
    }
}
