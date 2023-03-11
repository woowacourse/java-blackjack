import java.util.List;

import domain.card.RandomShuffleStrategy;
import domain.game.BlackJackGame;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = initGame();
        bet(blackJackGame);
        deal(blackJackGame);
        play(blackJackGame);
        showResults(blackJackGame);
    }

    private static BlackJackGame initGame() {
        try {
            List<String> names = InputView.requestNames();
            return BlackJackGame.from(names, new RandomShuffleStrategy());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return initGame();
        }
    }

    private static void bet(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.fetchPlayerNames();

        for (String name : playerNames) {
            Long betAmount = readBetAmount(name);
            // blackJackGame.assignBetAmount(name, betAmount);
        }
    }

    private static Long readBetAmount(String currentPlayerName) {
        try {
            return InputView.requestBetAmount(currentPlayerName);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readBetAmount(currentPlayerName);
        }
    }

    private static void deal(BlackJackGame blackJackGame) {
        blackJackGame.dealCardsToParticipants();

        List<String> playerNames = blackJackGame.fetchPlayerNames();
        OutputView.printDealFinishMessage(playerNames, blackJackGame.getInitHandCount());

        printInitialHands(blackJackGame);
        printBlackJackPlayer(blackJackGame);
    }

    private static void printInitialHands(BlackJackGame blackJackGame) {
        List<String> participantNames = blackJackGame.fetchParticipantNames();
        for (String participantName : participantNames) {
            OutputView.printParticipantCard(participantName, blackJackGame.fetchParticipantInitHand(participantName));
        }
        OutputView.printEmptyLine();
    }

    private static void printBlackJackPlayer(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.fetchPlayerNames();
        for (String playerName : playerNames) {
            printIfBlackJack(blackJackGame, playerName);
        }
    }

    private static void printIfBlackJack(BlackJackGame blackJackGame, String playerName) {
        if (blackJackGame.hasBlackJack(playerName)) {
            OutputView.printPlayerHasBlackJack(playerName);
        }
    }

    private static void play(BlackJackGame blackJackGame) {
        playPlayerTurns(blackJackGame);
        playDealerTurn(blackJackGame);
    }

    private static void playPlayerTurns(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.fetchNoBlackJackPlayerNames();

        for (String playerName : playerNames) {
            playTurn(blackJackGame, playerName);
            printIfBust(blackJackGame, playerName);
        }
    }

    private static void playTurn(BlackJackGame blackJackGame, String playerName) {
        String hitRequest;
        do {
            hitRequest = readDrawingCardRequest(playerName);
            blackJackGame.hitOrStay(hitRequest, playerName);
            List<String> playerHand = blackJackGame.fetchParticipantHand(playerName);
            OutputView.printParticipantCard(playerName, playerHand);
        } while (!blackJackGame.isBust(playerName) && !blackJackGame.isTurnOver(hitRequest));
    }

    private static String readDrawingCardRequest(String currentPlayer) {
        try {
            return InputView.requestDrawingCard(currentPlayer);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readDrawingCardRequest(currentPlayer);
        }
    }

    private static void printIfBust(BlackJackGame blackJackGame, String playerName) {
        if (blackJackGame.isBust(playerName)) {
            OutputView.printPlayerIsBust(playerName);
        }
    }

    private static void playDealerTurn(BlackJackGame blackJackGame) {
        if (blackJackGame.shouldDealerHit()) {
            blackJackGame.dealerHit();
            OutputView.printDealerPickCardMessage();
        }
    }

    private static void showResults(BlackJackGame blackJackGame) {
        printScores(blackJackGame);
        // printResults(blackJackGame);
    }

    private static void printScores(BlackJackGame blackJackGame) {
        String dealerName = blackJackGame.fetchDealerName();
        printParticipantScore(blackJackGame, dealerName);

        for (String name : blackJackGame.fetchPlayerNames()) {
            printParticipantScore(blackJackGame, name);
        }
    }

    private static void printParticipantScore(BlackJackGame blackJackGame, String name) {
        OutputView.printParticipantHandValue(
            name,
            blackJackGame.fetchParticipantHand(name),
            blackJackGame.fetchParticipantScore(name));
    }

    // private static void printResults(BlackJackGame blackJackGame) {
    //     OutputView.printResultInfo();
    //     blackJackGame.calculateResult();
    //     OutputView.printResult(blackJackGame.fetchDealerName(),
    //         blackJackGame.fetchParticipantResult(blackJackGame.fetchDealerName()));
    //     for (String name : blackJackGame.fetchParticipantNames()) {
    //         OutputView.printResult(name, blackJackGame.fetchParticipantResult(name));
    //     }
    // }
}
