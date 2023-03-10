package controller;

import domain.deck.Card;
import domain.deck.Deck;
import domain.game.BlackJackGame;
import domain.player.Amount;
import domain.player.Name;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {
    private static final int BLACK_JACK_NUMBER = 21;
    private static final String DEALER_NAME = "딜러";

    private List<Name> names;
    private BlackJackGame blackJackGame;

    public void start() {
        names = inputNames();
        final List<Amount> amounts = inputBattings();
        blackJackGame = generateBlackJackGame(amounts);

        outputCards();
        drawCardsPlayer();
        drawCardsDealer();

        outputCardResult();
        outputProfitResult();
    }

    private void outputProfitResult() {
        OutputView.printEmptyLine();
        OutputView.printProfitResult(blackJackGame.calculateProfits());
    }

    private List<Name> inputNames() {
        OutputView.printInputNames();
        return InputView.readNames();
    }

    private List<Amount> inputBattings() {
        final List<Amount> amounts = new ArrayList<>();
        for (final Name name : names) {
            OutputView.printEmptyLine();
            OutputView.printInputAmount(name);
            amounts.add(InputView.readBatting());
        }
        return amounts;
    }

    private BlackJackGame generateBlackJackGame(final List<Amount> amounts) {
        final Deck deck = new Deck();
        deck.shuffleDeck();
        final BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
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

    private void drawWhileYes(final Name name) {
        boolean answer = true;
        while (isAbleDraw(name) && answer) {
            OutputView.printOneMoreCard(name);
            answer = isOneMore(name);
        }
    }

    private boolean isAbleDraw(final Name name) {
        return blackJackGame.getPlayerScore(name) < BLACK_JACK_NUMBER;
    }

    private boolean isOneMore(final Name name) {
        if (InputView.readAnswer()) {
            blackJackGame.drawCardPlayer(name);
            OutputView.printPlayerCard(name, blackJackGame.getPlayerCards(name));
            return true;
        }
        OutputView.printPlayerCard(name, blackJackGame.getPlayerCards(name));
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
                new Name(DEALER_NAME),
                blackJackGame.getDealerCards(),
                blackJackGame.getDealerScore()
        );

        names.forEach(name ->
                OutputView.printCardResult(name, blackJackGame.getPlayerCards(name),
                        blackJackGame.getPlayerScore(name))
        );
    }
}
