package blackjack.controller;

import blackjack.domain.GameStatistic;
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
        Dealer dealer = generateDealer(cardDeck);
        OutputView.printInitGameState(gamblers, dealer);

        playTurnGambler(gamblers, cardDeck);
        playTurnDealer(dealer, cardDeck);

        OutputView.printCardAndPoint(gamblers, dealer);
        printGameResult(GameStatistic.of(dealer, gamblers), dealer);
    }

    private Dealer generateDealer(CardDeck cardDeck) {
        Dealer dealer = Dealer.of(cardDeck);
//        dealer.init(cardDeck);
        return dealer;
    }

    private Gamblers generatePlayers(CardDeck cardDeck) {
        List<Gambler> gamblerList = new ArrayList<>();
        String[] names = InputView.inputGamblerNames();
        for (String name : names) {
            gamblerList.add(generatePlayer(cardDeck, name));
        }
        return Gamblers.of(gamblerList);
    }

    private Gambler generatePlayer(CardDeck cardDeck, String name) {
        Name gamblerName = Name.of(name);
        double money = InputView.inputGamblerBetMoney(gamblerName);
        Gambler gambler = Gambler.of(gamblerName, money, cardDeck);
//        gambler.init(cardDeck);
        return gambler;
    }

    private void playTurnGambler(Gamblers gamblers, CardDeck cardDeck) {
        for (Gambler gambler : gamblers.getGamblers()) {
            chooseHitOrStay(cardDeck, gambler);
            printCardStateFirstQuestion(gambler);
        }
    }

    private void chooseHitOrStay(CardDeck cardDeck, Gambler gambler) {
        while (gambler.isHit() && InputView.inputOneMoreCard(gambler.getName())) {
            gambler.addCard(cardDeck.draw());
            OutputView.printPlayerCardState(gambler);
        }
        if (gambler.isHit()) {
            gambler.stay();
        }
    }

    private void printCardStateFirstQuestion(Gambler gambler) {
        if (gambler.isFirstQuestion()) {
            OutputView.printPlayerCardState(gambler);
        }
    }

    private void playTurnDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHit()) {
            dealer.addCard(cardDeck.draw());
            OutputView.printDealerCardAdded();
        }
        dealer.stay();
    }

    private void printGameResult(GameStatistic gameStatistic, Dealer dealer) {
        OutputView.printGameResult(gameStatistic, dealer);
    }
}
