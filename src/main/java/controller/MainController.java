package controller;

import domain.deck.Card;
import domain.deck.Deck;
import domain.game.BlackJackGame;
import domain.game.Outcome;
import view.InputView;
import view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private static final String DEALER_NAME = "딜러";

    private List<String> names;
    private BlackJackGame blackJackGame;

    public void start() {
        names = inputNames();
        blackJackGame = generateBlackJackGame();

        outputCards();
        drawCardsPlayer();
        drawCardsDealer();

        outputCardResult();
        outputGameResult();
    }

    private List<String> inputNames() {
        OutputView.printInputNames();
        return InputView.readNames();
    }

    private BlackJackGame generateBlackJackGame() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        BlackJackGame blackJackGame = new BlackJackGame(deck, names);
        OutputView.printEmptyLine();
        OutputView.printDistributeCard(names);
        return blackJackGame;
    }

    private void outputCards() {
        final Card dealerCard = blackJackGame.getCards(DEALER_NAME).get(0);
        OutputView.printDealerCard(dealerCard);
        names.forEach(name ->
                OutputView.printPlayerCard(name, blackJackGame.getCards(name))
        );
        OutputView.printEmptyLine();
    }

    private void drawCardsPlayer() {
        names.forEach(this::drawWhileYes);
    }

    private void drawWhileYes(final String playerName) {
        do {
            OutputView.printOneMoreCard(playerName);
        } while (isOneMore(playerName));
    }

    private boolean isOneMore(final String playerName) {
        if (InputView.readAnswer()) {
            blackJackGame.drawCard(playerName);
            OutputView.printPlayerCard(playerName, blackJackGame.getCards(playerName));
            return true;
        }
        OutputView.printPlayerCard(playerName, blackJackGame.getCards(playerName));
        return false;
    }

    private void drawCardsDealer() {
        OutputView.printEmptyLine();
        while (blackJackGame.isDealerDraw()) {
            OutputView.printDealerDrawCard();
            blackJackGame.drawCard(DEALER_NAME);
        }
        OutputView.printEmptyLine();
    }

    private void outputCardResult() {
        OutputView.printCardResult(
                DEALER_NAME,
                blackJackGame.getCards(DEALER_NAME),
                blackJackGame.getScore(DEALER_NAME)
        );

        names.forEach(name ->
                OutputView.printCardResult(name, blackJackGame.getCards(name), blackJackGame.getScore(name))
        );
    }

    private void outputGameResult() {
        final EnumMap<Outcome, Integer> dealerOutcome = blackJackGame.decideDealerOutcome();
        final Map<String, Outcome> playersOutcome = blackJackGame.decidePlayersOutcome();
        OutputView.printEmptyLine();
        OutputView.printGameResult(dealerOutcome, playersOutcome);
    }
}
