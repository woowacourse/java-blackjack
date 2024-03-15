package controller;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.money.Money;
import domain.money.PlayersMoney;
import domain.user.Hand;
import domain.user.Player;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    private static final int RECEIVABLE_THRESHOLD = 16;

    public void play() {
        Deck deck = new Deck(new DeckGenerator().generate());
        PlayersMoney playersMoney = new PlayersMoney(inputPlayersMoney(deck));
        Hand dealerHand = new Hand(deck.drawCard(), deck.drawCard());
        OutputView.printStartStatus(dealerHand, playersMoney.getPlayers());
        playersMoney.doPlayerTurn(InputView::inputAddCommand, deck);
        doDealerTurn(dealerHand, deck);
        doResult(playersMoney, dealerHand);
    }

    private Map<Player, Money> inputPlayersMoney(Deck deck) {
        return ExceptionHandler.handle(InputView::inputNames)
                .stream()
                .collect(Collectors.toMap(
                        name -> new Player(name, deck.drawCard(), deck.drawCard()),
                        name -> getValidatedMoney(name.value())
                ));
    }

    private Money getValidatedMoney(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputMoney(nameValue));
    }

    private void doDealerTurn(Hand dealerHand, Deck deck) {
        while (isReceivable(dealerHand)) {
            dealerHand.receiveCard(deck.drawCard());
            OutputView.printDealerHitMessage();
        }
    }

    private boolean isReceivable(Hand dealerHand) {
        return dealerHand.sumCard() <= RECEIVABLE_THRESHOLD;
    }

    private void doResult(PlayersMoney playersMoney, Hand dealerHand) {
        PlayersMoney resultPlayersMoney = playersMoney.changeByGameResult(dealerHand);
        printGameResult(dealerHand, resultPlayersMoney);
    }

    private void printGameResult(Hand dealerHand, PlayersMoney playersMoney) {
        OutputView.printAllUserCardsAndSum(dealerHand, playersMoney.getPlayers());
        OutputView.printFinalResult(playersMoney);
    }
}
