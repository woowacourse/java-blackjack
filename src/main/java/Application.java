import java.util.List;
import java.util.Map;

import domain.game.BlackJackGame;
import domain.game.Result;
import domain.people.Participant;
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
            return BlackJackGame.from(names);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return initGame();
        }
    }

    private static void deal(BlackJackGame blackJackGame) {
        blackJackGame.dealCardsToParticipants();
        List<String> playersNames = blackJackGame.getPlayerNames();
        OutputView.printDealFinishMessage(playersNames);

        printInitialHands(blackJackGame);
    }

    private static void printInitialHands(BlackJackGame blackJackGame) {
        for (Map.Entry<String, List<String>> participantHand : blackJackGame.getParticipantsInitHands().entrySet()) {
            OutputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        OutputView.printEmptyLine();
    }

    private static void play(BlackJackGame blackJackGame) {

        hitOrStayForPlayer(blackJackGame);
        hitOrStayForDealer(blackJackGame);
    }

    private static void hitOrStayForPlayer(BlackJackGame blackJackGame) {
        try {
            playEachTurn(blackJackGame);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            hitOrStayForPlayer(blackJackGame);
        }
    }

    private static void playEachTurn(BlackJackGame blackJackGame) {
        while (blackJackGame.isOngoing()) {
            String currentPlayer = blackJackGame.getCurrentPlayer();
            String hitRequest = InputView.requestDrawingCard(currentPlayer);

            blackJackGame.hitOrStay(hitRequest);

            List<String> currentPlayerHand = blackJackGame.getParticipantsHands().get(currentPlayer);
            OutputView.printParticipantCard(currentPlayer, currentPlayerHand);
        }
    }

    private static void hitOrStayForDealer(BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerHandUnderMinimumValue()) {
            blackJackGame.dealerHit();
            OutputView.printDealerPickCardMessage();
        }
    }

    private static void showResults(BlackJackGame blackJackGame) {
        printScores(blackJackGame);
        printResults(blackJackGame);
    }

    private static void printScores(BlackJackGame blackJackGame) {
        for (Map.Entry<Participant, String> participantScore : blackJackGame.getParticipantScores().entrySet()) {
            OutputView.printParticipantHandValue(participantScore.getKey().getName(),
                participantScore.getKey().getCardNames(), participantScore.getValue());
        }
    }

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printResultInfo();

        Map<String, Result> playerResults = blackJackGame.calculatePlayerResults();
        Map<Result, Integer> dealerResults = blackJackGame.calculateDealerResults(playerResults);
        OutputView.printDealerResult(dealerResults);
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            OutputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }
}
