package controller;

import domain.BlackjackGame;
import domain.DealerWinStatus;
import domain.Deck;
import domain.DeckGenerator;
import domain.GameResult;
import domain.ParticipantName;
import domain.Score;
import domain.TrumpCard;
import domain.WinStatus;
import domain.strategy.BlackjackDrawStrategy;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        List<ParticipantName> participantNames = handleInput(this::handleNames);
        Deck deck = DeckGenerator.generateDeck(new BlackjackDrawStrategy());
        BlackjackGame blackjackGame = new BlackjackGame(participantNames, deck);
        outputView.printInitiateDraw(participantNames);
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
        GameResult dealerGameResult = blackjackGame.currentDealerBlackjackResult();
        openPlayerResultCards(dealerGameResult);

        List<GameResult> playerGameResults = blackjackGame.currentPlayerBlackjackResult();
        for (GameResult gameResult : playerGameResults) {
            openPlayerResultCards(gameResult);
        }
    }

    private void blackjackWinnerResult(BlackjackGame blackjackGame) {
        Map<ParticipantName, WinStatus> playerWinStatuses = blackjackGame.getPlayerWinStatuses();
        DealerWinStatus dealerWinStatus = blackjackGame.calculateDealerWinStatus(playerWinStatuses);
        outputView.resultHeader();
        outputView.dealerWinStatus(dealerWinStatus.win(), dealerWinStatus.lose(), blackjackGame.dealerName());

        for (Entry<ParticipantName, WinStatus> winStatus : playerWinStatuses.entrySet()) {
            outputView.playerWinStatus(winStatus.getKey(), winStatus.getValue());
        }
    }

    private void openPlayerResultCards(GameResult gameResult) {
        ParticipantName name = gameResult.name();
        List<TrumpCard> trumpCards = gameResult.trumpCards();
        Score totalScore = gameResult.cardSum();

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
        List<GameResult> gameResults = blackjackGame.currentPlayerBlackjackResult();
        for (GameResult gameResult : gameResults) {
            ParticipantName name = gameResult.name();
            openPlayerCard(gameResult.trumpCards(), name);
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

    private List<ParticipantName> handleNames() {
        outputView.inputNames();
        List<String> names = inputView.inputNames();
        return ParticipantName.namesOf(names);
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
