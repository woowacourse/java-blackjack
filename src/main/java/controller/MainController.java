package controller;


import domain.deck.Card;
import domain.deck.Deck;
import domain.game.BlackJackGame;
import domain.game.Outcome;
import domain.player.Name;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class MainController {
    private static final int BLACK_JACK_NUMBER = 21;
    private static final String DEALER_NAME = "딜러";

    private List<String> names;
    private BlackJackGame blackJackGame;

    public void start() {
        names = inputNames();
        List<Integer> amounts = inputAmounts();
        blackJackGame = generateBlackJackGame(amounts);

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

    private List<Integer> inputAmounts() {
        List<Integer> amounts = new ArrayList<>();
        for (String name : names) {
            OutputView.printEmptyLine();
            OutputView.printInputAmount(name);
            amounts.add(InputView.readAmount());
        }
        return amounts;
    }

    private BlackJackGame generateBlackJackGame(List<Integer> amounts) {
        Deck deck = new Deck();
        deck.shuffleDeck();
        BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
        OutputView.printEmptyLine();
        OutputView.printDistributeCard(names);
        return blackJackGame;
    }

    private void outputCards() {
        final Card dealerCard = blackJackGame.getDealerCards().get(0);
        OutputView.printDealerCard(dealerCard);
        names.forEach(name ->
                OutputView.printPlayerCard(name, blackJackGame.getPlayerCards(name))
        );
        OutputView.printEmptyLine();
    }

    private void drawCardsPlayer() {
        names.forEach(this::drawWhileYes);
    }

    private void drawWhileYes(final String playerName) {
        boolean answer = true;
        while (isAbleDraw(playerName) && answer) {
            OutputView.printOneMoreCard(playerName);
            answer = isOneMore(playerName);
        }
    }

    private boolean isAbleDraw(final String playerName) {
        return blackJackGame.getPlayerScore(playerName) < BLACK_JACK_NUMBER;
    }

    private boolean isOneMore(final String playerName) {
        if (InputView.readAnswer()) {
            blackJackGame.drawCardPlayer(playerName);
            OutputView.printPlayerCard(playerName, blackJackGame.getPlayerCards(playerName));
            return true;
        }
        OutputView.printPlayerCard(playerName, blackJackGame.getPlayerCards(playerName));
        return false;
    }

    private void drawCardsDealer() {
        OutputView.printEmptyLine();
        while (blackJackGame.isDealerDraw()) {
            OutputView.printDealerDrawCard();
            blackJackGame.drawCardDealer();
        }
        OutputView.printEmptyLine();
    }

    private void outputCardResult() {
        OutputView.printCardResult(
                DEALER_NAME,
                blackJackGame.getDealerCards(),
                blackJackGame.getDealerScore()
        );

        names.forEach(name ->
                OutputView.printCardResult(name, blackJackGame.getPlayerCards(name), blackJackGame.getPlayerScore(name))
        );
    }

    private void outputGameResult() {
        final EnumMap<Outcome, Integer> dealerResult = blackJackGame.calculateDealerResult();
        final Map<Name, Outcome> playerResult = blackJackGame.decidePlayersOutcome();
        OutputView.printEmptyLine();
        OutputView.printGameResult(dealerResult, playerResult);
    }
}
