package controller;

import domain.Deck;
import domain.DeckGenerator;
import domain.BlackjackGame;
import domain.Result;
import domain.DealerWinStatus;
import domain.ParticipantName;
import domain.Player;
import domain.Score;
import domain.TrumpCard;
import domain.WinStatus;
import domain.strategy.BlackjackDrawStrategy;
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

    public void run() {
        List<String> names = handleInput(this::handleNames);
        Deck deck = DeckGenerator.generateDeck(new BlackjackDrawStrategy());
        BlackjackGame blackjackGame = new BlackjackGame(names, deck);
        outputView.printInitiateDraw(names);
        openFirstDealerCard(blackjackGame);
        openPlayerCards(blackjackGame);
        askPlayerDraw(blackjackGame);
        dealerHit(blackjackGame);
        blackjackGameResult(blackjackGame);
    }

    private void blackjackGameResult(BlackjackGame blackjackGame) {
        blackjackCardResult(blackjackGame);

        blackjackWinnerResult(blackjackGame);
    }

    private void blackjackCardResult(BlackjackGame blackjackGame) {
        Result dealerResult = blackjackGame.currentDealerBlackjackResult();
        openPlayerResultCards(dealerResult);

        List<Result> playerResults = blackjackGame.currentPlayerBlackjackResult();
        for (Result result : playerResults) {
            openPlayerResultCards(result);
        }
    }

    private void blackjackWinnerResult(BlackjackGame blackjackGame) {
        DealerWinStatus dealerWinStatus = blackjackGame.getDealerWinStatus();
        Map<ParticipantName, WinStatus> playerWinStatuses = blackjackGame.getPlayerWinStatuses();

        outputView.resultHeader();
        outputView.dealerWinStatus(dealerWinStatus.win(), dealerWinStatus.lose(), blackjackGame.dealerName());
        for (ParticipantName name : playerWinStatuses.keySet()) {
            outputView.playerWinStatus(name, playerWinStatuses.get(name));
        }
    }

    private void openPlayerResultCards(Result result) {
        ParticipantName name = result.name();
        List<TrumpCard> trumpCards = result.trumpCards();
        Score totalScore = result.cardSum();

        outputView.openCardsWithSum(name, trumpCards, totalScore);
    }

    private void dealerHit(BlackjackGame blackjackGame) {
        while (blackjackGame.dealerDrawable()) {
            outputView.dealerHit();
            blackjackGame.dealerHit();
        }
    }

    private void askPlayerDraw(BlackjackGame blackjackGame) {
        List<ParticipantName> names = blackjackGame.playerNames();
        for (ParticipantName name : names) {
            handleAskDraw(name, blackjackGame);
        }
    }

    private void handleAskDraw(ParticipantName name, BlackjackGame blackjackGame) {
        boolean isDraw = handleAskDraw(name);
        if (!isDraw) {
            return;
        }
        blackjackGame.dealCard(name);
        openPlayerCards(name, blackjackGame);
        if (blackjackGame.isBust(name)) {
            return;
        }
        handleAskDraw(name, blackjackGame);
    }

    private void openPlayerCards(ParticipantName name, BlackjackGame blackjackGame) {
        List<TrumpCard> trumpCards = blackjackGame.playerCards(name);
        openPlayerCard(trumpCards, name);
    }

    private boolean handleAskDraw(ParticipantName name) {
        return handleInput(() -> {
            outputView.askDraw(name);
            return inputView.askDraw();
        });
    }

    private void openPlayerCards(BlackjackGame blackjackGame) {
        List<Result> results = blackjackGame.currentPlayerBlackjackResult();
        for (Result result : results) {
            ParticipantName name = result.name();
            openPlayerCard(result.trumpCards(), name);
        }
    }

    private void openPlayerCard(List<TrumpCard> trumpCards, ParticipantName name) {
        outputView.openCards(name, trumpCards);
    }

    private void openFirstDealerCard(BlackjackGame blackjackGame) {
        ParticipantName name = blackjackGame.dealerName();
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
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return handleInput(inputSupplier);
        }
    }
}
