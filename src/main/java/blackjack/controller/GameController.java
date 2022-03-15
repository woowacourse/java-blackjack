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
        Dealer dealer = initDealer(cardDeck);
        Gamblers gamblers = initPlayers(cardDeck);
        OutputView.printInitGameState(gamblers, dealer);

        askPlayersOneMoreCard(gamblers, cardDeck);
        addDealerOneMoreCard(dealer, cardDeck);

        OutputView.printCardAndPoint(gamblers, dealer);

        Statistic statistic = Statistic.of(dealer, gamblers);
        printGameResult(statistic, gamblers);
    }

    private Dealer initDealer(CardDeck cardDeck) {
        Dealer dealer = Dealer.of();
        dealer.addCard(cardDeck.draw());
        dealer.addCard(cardDeck.draw());
        return dealer;
    }

    private Gamblers initPlayers(CardDeck cardDeck) {
        Gamblers gamblers = generatePlayers();
        gamblers.distributeCard(cardDeck);
        gamblers.distributeCard(cardDeck);
        return gamblers;
    }

    private Gamblers generatePlayers() {
        List<Gambler> gamblerList = new ArrayList<>();
        String[] names = InputView.inputGamblerName();
        for (String name : names) {
            gamblerList.add(Gambler.of(Name.of(name)));
        }
        return Gamblers.of(gamblerList);
    }

    private void askPlayersOneMoreCard(Gamblers gamblers, CardDeck cardDeck) {
        for (Gambler gambler : gamblers.getCardNeedGamblers()) {
            askOneMoreCardByPlayer(gambler, cardDeck);
        }
    }

    private void addDealerOneMoreCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(cardDeck.draw());
            OutputView.printDealerCardAdded();
        }
    }

    private void askOneMoreCardByPlayer(Gambler gambler, CardDeck cardDeck) {
        if (gambler.isOneMoreCard()) {
            askNeedCardPlayer(gambler, cardDeck);
            checkFirstQuestion(gambler);
        }
    }

    private void askNeedCardPlayer(Gambler gambler, CardDeck cardDeck) {
        if (InputView.inputOneMoreCard(gambler.getName())) {
            gambler.addCard(cardDeck.draw());
            OutputView.printPlayerCardState(gambler);
            askOneMoreCardByPlayer(gambler, cardDeck);
        }
    }

    private void checkFirstQuestion(Gambler gambler) {
        if (gambler.getCards().size() <= DEFAULT_CARD_AMOUNT) {
            OutputView.printPlayerCardState(gambler);
        }
    }

    private void printGameResult(Statistic statistic, Gamblers gamblers) {
        OutputView.printTotalResult(statistic);
        OutputView.printTotalResultByGambler(statistic, gamblers);
    }
}
