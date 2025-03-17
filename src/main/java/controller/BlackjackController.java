package controller;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.card.strategy.BlackjackDrawStrategy;
import domain.game.BlackjackGame;
import domain.game.GameResult;
import domain.participant.Bet;
import domain.participant.ParticipantName;
import domain.participant.Score;
import java.util.HashMap;
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
        BlackjackGame blackjackGame = startBlackjack();
        playBlackjack(blackjackGame);
        printBlackjackResult(blackjackGame);
    }


    private BlackjackGame startBlackjack() {
        try {
            List<ParticipantName> names = handleInput(this::handleNames);
            Map<ParticipantName, Bet> playerBets = inputPlayerBets(names);
            Deck deck = new Deck(new BlackjackDrawStrategy());
            return new BlackjackGame(names, playerBets, deck);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return startBlackjack();
        }
    }

    private List<ParticipantName> handleNames() {
        outputView.inputNames();
        return inputView.inputNames()
                .stream()
                .map(ParticipantName::new)
                .toList();
    }

    private Map<ParticipantName, Bet> inputPlayerBets(List<ParticipantName> participantNames) {
        Map<ParticipantName, Bet> playerBets = new HashMap<>();
        for (ParticipantName participantName : participantNames) {
            int betAmount = handleBetAmount(participantName);
            Bet playerBet = new Bet(betAmount);
            playerBets.putIfAbsent(participantName, playerBet);
        }
        return playerBets;
    }

    private int handleBetAmount(ParticipantName participantName) {
        return handleInput(() -> {
            outputView.inputBetAmounts(participantName);
            return inputView.inputNumber();
        });
    }


    private void playBlackjack(BlackjackGame blackjackGame) {
        printStartCards(blackjackGame);
        playPlayerTurn(blackjackGame);
        dealerHit(blackjackGame);
    }

    private void printStartCards(BlackjackGame blackjackGame) {
        outputView.printStartDraw(blackjackGame);
        openFirstDealerCard(blackjackGame);
        openAllPlayerCards(blackjackGame);
    }

    private void openFirstDealerCard(BlackjackGame blackjackGame) {
        ParticipantName name = blackjackGame.dealerName();
        TrumpCard trumpCard = blackjackGame.dealerCardFirst();
        outputView.openCards(name, List.of(trumpCard));
    }

    private void openAllPlayerCards(BlackjackGame blackjackGame) {
        for (ParticipantName participantName : blackjackGame.playerNames()) {
            openPlayerCards(participantName, blackjackGame);
        }
    }

    private void openPlayerCards(ParticipantName name, BlackjackGame blackjackGame) {
        List<TrumpCard> trumpCards = blackjackGame.playerCards(name);
        outputView.openCards(name, trumpCards);
    }


    private void playPlayerTurn(BlackjackGame blackjackGame) {
        List<ParticipantName> names = blackjackGame.playerNames();
        for (ParticipantName name : names) {
            handlePlayerTurn(name, blackjackGame);
        }
    }

    private void handlePlayerTurn(ParticipantName name, BlackjackGame blackjackGame) {
        while (!blackjackGame.isFinished(name)) {
            boolean isDraw = handleAskDraw(name);
            processDrawOrStay(name, blackjackGame, isDraw);
            openPlayerCards(name, blackjackGame);
        }
    }

    private void processDrawOrStay(ParticipantName name, BlackjackGame blackjackGame, boolean isDraw) {
        if (!isDraw) {
            blackjackGame.stayPlayer(name);
            return;
        }
        blackjackGame.dealCard(name);
    }

    private boolean handleAskDraw(ParticipantName name) {
        return handleInput(() -> {
            outputView.askDraw(name);
            return inputView.askDraw();
        });
    }

    private void dealerHit(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerDrawable()) {
            outputView.dealerHit();
            blackjackGame.dealerHit();
        }
    }

    private void printBlackjackResult(BlackjackGame blackjackGame) {
        blackjackCardResult(blackjackGame);
        blackjackProfitResult(blackjackGame);
    }

    private void blackjackCardResult(BlackjackGame blackjackGame) {
        GameResult dealerGameResult = blackjackGame.currentDealerBlackjackResult();
        openPlayerResultCards(dealerGameResult);

        List<GameResult> playerGameResults = blackjackGame.currentPlayerBlackjackResult();
        for (GameResult gameResult : playerGameResults) {
            openPlayerResultCards(gameResult);
        }
    }

    private void openPlayerResultCards(GameResult gameResult) {
        ParticipantName name = gameResult.name();
        List<TrumpCard> trumpCards = gameResult.trumpCards();
        Score totalScore = gameResult.cardSum();

        outputView.openCardsWithSum(name, trumpCards, totalScore);
    }

    private void blackjackProfitResult(BlackjackGame blackjackGame) {
        Map<String, Integer> playersProfit = blackjackGame.getPlayersProfit();
        Map<String, Integer> dealerProfit = blackjackGame.getDealerProfit(playersProfit);
        outputView.printProfitResult(playersProfit, dealerProfit);
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
