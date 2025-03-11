package controller;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.BlackjackResult;
import domain.blackjackgame.BlackjackWinner;
import domain.blackjackgame.DealerWinStatus;
import domain.blackjackgame.PlayerGameResult;
import domain.blackjackgame.TrumpCard;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.strategy.BlackjackDeckGenerateStrategy;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import exception.BlackJackException;
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
        List<String> names = handleInput(this::handleNames);
        startBlackjack(names);
    }

    private void startBlackjack(List<String> names) {
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new BlackjackDeckGenerateStrategy());
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
        Map<String, PlayerGameResult> playerWinStatuses = blackjackWinner.getPlayerWinStatuses();

        outputView.resultHeader();
        outputView.dealerWinStatus(dealerWinStatus.win(), dealerWinStatus.lose(), blackjackGame.dealerName());
        for (String name : playerWinStatuses.keySet()) {
            outputView.playerWinStatus(name, playerWinStatuses.get(name));
        }
    }

    private void openPlayerResultCards(BlackjackResult blackjackResult) {
        String name = blackjackResult.name();
        List<TrumpCard> cardHands = blackjackResult.cardHands();
        int sum = blackjackResult.cardSum();

        outputView.openCardsWithSum(name, cardHands, sum);
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
        List<TrumpCard> cardHands = blackjackGame.playerCards(name);
        openPlayerCard(cardHands, name);
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
            openPlayerCard(blackjackResult.cardHands(), name);
        }
    }

    private void openPlayerCard(List<TrumpCard> cardHands, String name) {
        outputView.openCards(name, cardHands);
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
