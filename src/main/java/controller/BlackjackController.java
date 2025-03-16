package controller;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.BlackjackResult;
import domain.blackjackgame.TrumpCard;
import domain.participant.BetManager;
import domain.participant.BlackjackBet;
import domain.strategy.BlackjackDeckGenerateStrategy;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import exception.BlackJackException;
import java.util.ArrayList;
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
        List<Integer> bets = new ArrayList<>();
        for (String name : names) {
            bets.add(handleInput(() -> handleBets(name)));
        }
        startBlackjack(names, bets);
    }

    private int handleBets(String name) {
        outputView.inputBets(name);
        int bet = inputView.inputBet();
        validateBet(bet);
        return bet;
    }

    private void validateBet(int bet) {
        new BlackjackBet(bet);
    }

    private List<String> handleNames() {
        outputView.inputNames();
        return inputView.inputNames();
    }

    private void startBlackjack(List<String> names, List<Integer> bets) {
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new BlackjackDeckGenerateStrategy());
        BlackjackGame blackjackGame = BlackjackGame.bettingBlackjackGame(deck, names, bets);
        BetManager betManager = new BetManager(blackjackGame);
        outputView.printInitiateDraw(names);
        openFirstDealerCard(blackjackGame);
        openPlayerCards(blackjackGame);
        askPlayerDraw(blackjackGame);
        dealerHit(blackjackGame);
        bettingBlackjackGameResult(blackjackGame, betManager);
    }

    private void openFirstDealerCard(BlackjackGame blackjackGame) {
        String name = blackjackGame.dealerName();
        TrumpCard trumpCard = blackjackGame.dealerCardFirst();
        outputView.openCards(name, List.of(trumpCard));
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

    private void askPlayerDraw(BlackjackGame blackjackGame) {
        List<String> names = blackjackGame.playerNames();
        for (String name : names) {
            handleAskDraw(name, blackjackGame);
        }
    }

    private void handleAskDraw(String name, BlackjackGame blackjackGame) {
        while (!blackjackGame.isBust(name) && handleAskDrawToUser(name)) {
            blackjackGame.drawCard(name);
            openPlayerCards(name, blackjackGame);
        }
    }

    private boolean handleAskDrawToUser(String name) {
        return handleInput(() -> {
            outputView.askDraw(name);
            return inputView.askDraw();
        });
    }

    private void openPlayerCards(String name, BlackjackGame blackjackGame) {
        List<TrumpCard> cardHands = blackjackGame.playerCards(name);
        openPlayerCard(cardHands, name);
    }

    private void dealerHit(BlackjackGame blackjackGame) {
        while (blackjackGame.dealerDrawable()) {
            outputView.dealerHit();
            blackjackGame.dealerHit();
        }
    }

    private void bettingBlackjackGameResult(BlackjackGame blackjackGame, BetManager betManager) {
        openParticipantsCards(blackjackGame);
        Map<String, Double> blackjackBettingResult = betManager.blackjackBettingResult();
        outputView.printBettingBlackjackGameResult(blackjackBettingResult);
    }

    private void openParticipantsCards(BlackjackGame blackjackGame) {
        BlackjackResult dealerResult = blackjackGame.currentDealerBlackjackResult();
        openPlayerResultCards(dealerResult);

        List<BlackjackResult> playerResults = blackjackGame.currentPlayerBlackjackResult();
        for (BlackjackResult result : playerResults) {
            openPlayerResultCards(result);
        }
    }

    private void openPlayerResultCards(BlackjackResult blackjackResult) {
        String name = blackjackResult.name();
        List<TrumpCard> cardHands = blackjackResult.cardHands();
        int sum = blackjackResult.cardSum();
        outputView.openCardsWithSum(name, cardHands, sum);
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
