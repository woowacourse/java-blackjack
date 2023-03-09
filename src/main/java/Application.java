import java.util.List;
import java.util.Map;

import domain.card.RandomShuffleStrategy;
import domain.game.BlackJackGame;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = initGame();

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

    private static void deal(BlackJackGame blackJackGame) {
        blackJackGame.dealCardsToParticipants();
        List<String> playersNames = blackJackGame.fetchPlayerNames();
        OutputView.printDealFinishMessage(playersNames);

        printInitialHands(blackJackGame);
    }

    private static void printInitialHands(BlackJackGame blackJackGame) {
        for (Map.Entry<String, List<String>> participantHand : blackJackGame.fetchParticipantsInitHands().entrySet()) {
            OutputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        OutputView.printEmptyLine();
    }

    private static void play(BlackJackGame blackJackGame) {

        playPlayerTurns(blackJackGame);
        playDealerTurn(blackJackGame);
    }

    private static void playPlayerTurns(BlackJackGame blackJackGame) {
        while (blackJackGame.isPlayerTurnsOngoing()) {
            String currentPlayer = blackJackGame.fetchCurrentPlayerName();
            String hitRequest = readDrawingCardRequest(currentPlayer);

            blackJackGame.hitOrStay(hitRequest);

            List<String> currentPlayerHand = blackJackGame.fetchParticipantsHands().get(currentPlayer);
            OutputView.printParticipantCard(currentPlayer, currentPlayerHand);
        }
    }

    private static String readDrawingCardRequest(String currentPlayer) {
        try {
            return InputView.requestDrawingCard(currentPlayer);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readDrawingCardRequest(currentPlayer);
        }
    }

    private static void playDealerTurn(BlackJackGame blackJackGame) {
        while (blackJackGame.shouldDealerHit()) {
            blackJackGame.dealerHit();
            OutputView.printDealerPickCardMessage();
        }
    }

    private static void showResults(BlackJackGame blackJackGame) {
        printScores(blackJackGame);
        printResults(blackJackGame);
    }

    private static void printScores(BlackJackGame blackJackGame) {
        for (Map.Entry<String, String> participantScore : blackJackGame.fetchParticipantScores().entrySet()) {
            OutputView.printParticipantHandValue(participantScore.getKey(),
                blackJackGame.fetchParticipantsHands().get(participantScore.getKey()), participantScore.getValue());
        }
    }

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printResultInfo();

        Map<String, String> playerResults = blackJackGame.calculatePlayerResults();
        Map<String, Integer> dealerResults = blackJackGame.calculateDealerResults(playerResults);
        OutputView.printDealerResult(dealerResults);
        for (Map.Entry<String, String> playerResult : playerResults.entrySet()) {
            OutputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }
}
