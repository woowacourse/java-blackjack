package blackjack.controller;

import blackjack.domain.GameStatistic;
import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Name;
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

        hitOrStandGambler(gamblers, cardDeck);
        hitOrStandDealer(dealer, cardDeck);

        OutputView.printCardAndPoint(gamblers, dealer);
        GameStatistic statistic = GameStatistic.of(dealer, gamblers);
        printGameResult(statistic, dealer);
    }

    private Gamblers generatePlayers(CardDeck cardDeck) {
        List<Gambler> gamblerList = new ArrayList<>();
        String[] names = InputView.inputGamblerNames();
        for (String name : names) {
            double money = (double) InputView.inputGamblerBetMoney(name);
            gamblerList.add(Gambler.of(Name.of(name), money, cardDeck));
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
        gambler.stay();
    }

    private void printStateAtFirstQuestion(Gambler gambler) {
        if (gambler.isFirstQuestion()) {
            OutputView.printPlayerCardState(gambler);
        }
    }

    private void printGameResult(GameStatistic gameStatistic, Dealer dealer) {
        OutputView.printGameResult(gameStatistic, dealer);
    }
}
