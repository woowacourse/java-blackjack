import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            int betAmount = readBetAmount(name);
            blackJackGame.assignBetAmount(name, betAmount);
        }
    }

    private static int readBetAmount(String currentPlayerName) {
        try {
            OutputView.printEmptyLine();
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
        } while (!(blackJackGame.isBust(playerName) || blackJackGame.isTurnOver(hitRequest)));
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
        printResults(blackJackGame);
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

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printResultInfo();

        LinkedHashMap<String, Integer> playersAndProfits = new LinkedHashMap<>();

        printDealerResult(blackJackGame, playersAndProfits);
        printPlayersResults(playersAndProfits);
    }

    private static void printDealerResult(BlackJackGame blackJackGame,
        LinkedHashMap<String, Integer> playersAndProfits) {
        int dealerProfit = calculateDealerProfit(blackJackGame, playersAndProfits);
        OutputView.printResult(blackJackGame.fetchDealerName(), dealerProfit);
    }

    private static int calculateDealerProfit(BlackJackGame blackJackGame,
        LinkedHashMap<String, Integer> playersAndProfits) {
        for (String name : blackJackGame.fetchPlayerNames()) {
            playersAndProfits.put(name, blackJackGame.fetchPlayerProfit(name));
        }
        return -playersAndProfits.values().stream().mapToInt(profit -> profit).sum();
    }

    private static void printPlayersResults(LinkedHashMap<String, Integer> playersAndProfits) {
        for (Map.Entry<String, Integer> playerAndProfit : playersAndProfits.entrySet()) {
            OutputView.printResult(playerAndProfit.getKey(), playerAndProfit.getValue());
        }
    }

}
