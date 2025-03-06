package controller;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.BlackjackGame;
import domain.BlackjackResult;
import domain.BlackjackWinner;
import domain.Dealer;
import domain.DealerWinStatus;
import domain.Player;
import domain.TrumpCard;
import domain.WinStatus;
import domain.strategy.BlackjackDrawStrategy;
import except.BlackJackException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startBlackjack() {
        BlackjackDeckGenerator.generateDeck(new BlackjackDrawStrategy());
        List<String> names = handleInput(this::handleNames);
        startBlackjack(names);
    }

    private void startBlackjack(List<String> names) {
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new BlackjackDrawStrategy());
        BlackjackGame blackjackGame = new BlackjackGame(names, deck, new Dealer());
        outputView.printInitiateDraw(names);
        openFirstDealerCard(blackjackGame);
        openPlayerCards(blackjackGame);
        askPlayerDraw(blackjackGame);
        dealerHit(blackjackGame);
        blackjackGameResult(blackjackGame);
    }

    private void blackjackGameResult(BlackjackGame blackjackGame) {
        BlackjackResult dealerResult = blackjackGame.currentDealerBlackjackResult();
        openPlayerResultCards(dealerResult);

        List<BlackjackResult> playerResults = blackjackGame.currentPlayerBlackjackResult();
        for (BlackjackResult result : playerResults) {
            openPlayerResultCards(result);
        }

        blackjackWinnerResult(blackjackGame, dealerResult, playerResults);
    }

    private void blackjackWinnerResult(BlackjackGame blackjackGame, BlackjackResult dealerResult,
                                       List<BlackjackResult> playerResults) {
        BlackjackWinner blackjackWinner = new BlackjackWinner(dealerResult, playerResults);
        DealerWinStatus dealerWinStatus = blackjackWinner.getDealerWinStatus();
        Map<String, WinStatus> playerWinStatuses = blackjackWinner.getPlayerWinStatuses();

        outputView.resultHeader();
        outputView.dealerWinStatus(dealerWinStatus.win(), dealerWinStatus.lose(), blackjackGame.dealerName());
        for (String name : playerWinStatuses.keySet()) {
            outputView.playerWinStatus(name, playerWinStatuses.get(name));
        }
    }

    private void openPlayerResultCards(BlackjackResult blackjackResult) {
        String name = blackjackResult.name();
        List<TrumpCard> trumpCards = blackjackResult.trumpCards();
        int sum = blackjackResult.cardSum();

        outputView.openCardsWithSum(name, trumpCards, sum);
    }

    private void dealerHit(BlackjackGame blackjackGame) {
        while (blackjackGame.dealerDrawable()) {
            outputView.dealerHit();
            blackjackGame.dealerHit();
        }
    }

    private void askPlayerDraw(BlackjackGame blackjackGame) {
        List<String> names = blackjackGame.playerNames();
        for (String name : names) {
            handleAskDraw(name, blackjackGame);
        }
    }

    private void handleAskDraw(String name, BlackjackGame blackjackGame) {
        boolean isDraw = handleAskDraw(name);
        if (!isDraw) {
            return;
        }
        blackjackGame.drawCard(name);
        openPlayerCards(name, blackjackGame);
        if (blackjackGame.isBust(name)) {
            return;
        }
        handleAskDraw(name, blackjackGame);
    }

    private void openPlayerCards(String name, BlackjackGame blackjackGame) {
        List<TrumpCard> trumpCards = blackjackGame.playerCards(name);
        openPlayerCard(trumpCards, name);
    }

    private boolean handleAskDraw(String name) {
        return handleInput(() -> {
            outputView.askDraw(name);
            return inputView.askDraw();
        });
    }


    private void openPlayerCards(BlackjackGame blackjackGame) {
        List<BlackjackResult> blackjackResults = blackjackGame.currentPlayerBlackjackResult();
        for (BlackjackResult blackjackResult : blackjackResults) {
            String name = blackjackResult.name();
            openPlayerCard(blackjackResult.trumpCards(), name);
        }
    }

    private void openPlayerCard(List<TrumpCard> trumpCards, String name) {
        outputView.openCards(name, trumpCards);
    }

    private void openFirstDealerCard(BlackjackGame blackjackGame) {
        String name = blackjackGame.dealerName();
        TrumpCard trumpCard = blackjackGame.dealerCardFirst();
        outputView.openCards(name, List.of(trumpCard));
    }

    private List<String> handleNames() {
        outputView.inputNames();
        List<String> names = inputView.inputNames();
        validateNames(names);
        return names;
    }

    private void validateNames(List<String> names) {
        for (String name : names) {
            new Player(name);
        }
    }

    private <T> T handleInput(Supplier<T> inputSupplier) {
        try {
            return inputSupplier.get();
        } catch (BlackJackException e) {
            outputView.printError(e.getMessage());
            return handleInput(inputSupplier);
        }
    }
}
