package controller;

import domain.*;

import java.util.List;

import domain.shuffle.RandomCardStrategy;
import view.mesage.InputMessage;
import utils.InputParser;
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
        List<String> userNames = getUserNames();
        Deck deck = Deck.of(new RandomCardStrategy());

        Dealer dealer = Dealer.of(deck.drawInitialHand());
        Players players = Players.of(deck, userNames);
        BlackjackResult blackjackResult = BlackjackResult.of();

        askPlayerBets(players, blackjackResult);
        printInitialCards(players, dealer);
        askPlayerAddCard(players, deck, dealer);
        blackjackResult.resolveResults(players, dealer);

        printGameResult(blackjackResult);
    }

    private List<String> getUserNames() {
        List<String> names = null;
        while (names == null) {
            names = readUserNames();
        }
        return names;
    }

    private List<String> readUserNames() {
        try {
            String rawUserNames = inputView.askUsersName();
            return InputParser.splitByDelimiter(rawUserNames);
        } catch (IllegalArgumentException exception) {
            outputView.printLine(exception.getMessage());
            return null;
        }
    }

    private void askPlayerBets(Players players, BlackjackResult blackjackResult) {
        for (Player player : players.getPlayers()) {
            long betAmount = askBetAmount(player);
            blackjackResult.add(player, betAmount);
        }
    }

    private long askBetAmount(Player player) {
        System.out.println();
        BettingValidator bettingValidator = BettingValidator.of();
        while (true) {
            try {
                String betAmount = inputView.askBetAmount(player);
                bettingValidator.validateBetAmount(betAmount);
                return Long.parseLong(betAmount);
            } catch (IllegalArgumentException exception) {
                outputView.printLine(exception.getMessage());
            }
        }
    }

    private void printInitialCards(Players players, Dealer dealer) {
        outputView.initialDrawMessage(players.getPlayersName());
        outputView.printDealerCards(dealer);
        outputView.printPlayersCards(players);
    }

    private void askPlayerAddCard(Players players, Deck deck, Dealer dealer) {
        askPlayersAddCard(players, deck);
        addDealerCard(dealer, deck);
    }

    private void askPlayersAddCard(Players players, Deck deck) {
        outputView.printEmptyLine();
        for (Player player : players.getPlayers()) {
            askPlayerAddCard(player, deck);
        }
    }

    private void askPlayerAddCard(Player player, Deck deck) {
        String answer = InputMessage.USER_INPUT_YES.getMessage();

        while (shouldDrawCard(player, answer)) {
            answer = readAddCardAnswer(player);
            boolean wantsCard = sayYes(answer);
            processAdditionalCard(player, deck, wantsCard);
        }
    }

    private boolean shouldDrawCard(Player player, String answer) {
        HitDecision.from(answer);
        return !player.isBust() && sayYes(answer);
    }

    private String readAddCardAnswer(Player player) {
        while (true) {
            try {
                String answer = inputView.askAddCard(player.getName());
                HitDecision.from(answer);
                return answer;
            } catch (IllegalArgumentException exception) {
                outputView.printLine(exception.getMessage());
            }
        }
    }

    private void processAdditionalCard(Player player, Deck deck, boolean wantsCard) {
        if (wantsCard) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player);
            return;
        }
        if (player.isInitialHand()) {
            outputView.printPlayerCards(player);
        }
    }

    private boolean sayYes(String answer) {
        return HitDecision.from(answer) == HitDecision.YES;
    }

    private void addDealerCard(Dealer dealer, Deck deck) {
        while (dealer.shouldHit()) {
            outputView.dealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }

    private void printGameResult(BlackjackResult blackjackResult) {
        outputView.printFinalResult(blackjackResult);
    }
}
