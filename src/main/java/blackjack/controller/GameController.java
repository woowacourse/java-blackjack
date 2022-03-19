package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static final int DEFAULT_CARD_AMOUNT = 2;

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Gamblers gamblers = generatePlayers(cardDeck);
        Dealer dealer = Dealer.of(cardDeck);
        OutputView.printInitGameState(gamblers, dealer);

        /**
         * 1. HIT 인지 확인
         * -> 우선, ifFinished -> false
         * ->
         */
        hitOrStandGambler(gamblers, cardDeck);
        hitOrStandDealer(dealer, cardDeck);
//
//        OutputView.printCardAndPoint(gamblers, dealer);
//        printGameResult(Statistic.of(dealer, gamblers), gamblers);
    }

    private Gamblers generatePlayers(CardDeck cardDeck) {
        List<Gambler> gamblerList = new ArrayList<>();
        String[] names = InputView.inputGamblerNames();
        for (String name : names) {
            gamblerList.add(Gambler.of(Name.of(name), cardDeck));
        }
        return Gamblers.of(gamblerList);
    }

    private void hitOrStandGambler(Gamblers gamblers, CardDeck cardDeck) {
        for (Gambler gambler : gamblers.findHitGambler()) {
            askHitOrStand(gambler, cardDeck);
        }
    }

    private void hitOrStandDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHit()) {
            dealer.addCard(cardDeck.draw());
            OutputView.printDealerCardAdded();
        }
    }

    private void askHitOrStand(Gambler gambler, CardDeck cardDeck) {
        decideHitGambler(gambler, cardDeck);
        printStateAtFirstQuestion(gambler);
    }

    private void decideHitGambler(Gambler gambler, CardDeck cardDeck) {
        if (InputView.inputOneMoreCard(gambler.getName())) {
            gambler.addCard(cardDeck.draw());
            OutputView.printPlayerCardState(gambler);
            askHitOrStand(gambler, cardDeck);
        }
    }

    private void printStateAtFirstQuestion(Gambler gambler) {
        if (gambler.isFirstQuestion()) {
            OutputView.printPlayerCardState(gambler);
        }
    }

    private void printGameResult(Statistic statistic, Gamblers gamblers) {
        OutputView.printTotalResult(statistic);
        OutputView.printTotalResultByGambler(statistic, gamblers);
    }
}
