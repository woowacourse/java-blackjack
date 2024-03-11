package view;

import java.util.List;
import model.dto.DealerScoreResult;
import model.dto.GameCompletionResult;
import model.dto.PlayerScoreResult;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = " 카드: ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void print(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(GameCompletionResult dealerResult, List<GameCompletionResult> playersResult) {
        System.out.print(System.lineSeparator());
        List<String> playerNameTexts = playersResult.stream()
                .map(GameCompletionResult::getPartipantNameAsString)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        print("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResult);
        printPlayersFaceUp(playersResult);
        System.out.print(System.lineSeparator());
    }

    private static void printDealerHoleCard(GameCompletionResult gameCompletionResult) {
        print(gameCompletionResult.getPartipantNameAsString() + NAME_CARD_DELIMITER + gameCompletionResult.getCardsAsStrings()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayersFaceUp(List<GameCompletionResult> gameCompletionResults) {
        gameCompletionResults.forEach(OutputView::printSinglePlayerFaceUp);
    }

    public static void printSinglePlayerFaceUp(GameCompletionResult result) {
        print(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()));
    }

    public static void printFinalFaceUpResult(GameCompletionResult dealerGameCompletionResult, List<GameCompletionResult> playerGameCompletionResults) {
        System.out.print(System.lineSeparator());
        printSinglePlayerFinalFaceUp(dealerGameCompletionResult);
        playerGameCompletionResults.forEach(OutputView::printSinglePlayerFinalFaceUp);
    }

    private static void printSinglePlayerFinalFaceUp(GameCompletionResult result) {
        print(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()) + " - 결과: " + result.hand());
    }

    public static void printScoreResults(DealerScoreResult dealerScoreResult,
                                         List<PlayerScoreResult> playerScoreResults) {
        StringBuilder dealerScoresText = generateDealerScoreTexts(dealerScoreResult);

        System.out.print(System.lineSeparator());
        print("## 최종 승패");
        print(dealerScoresText.toString());
        playerScoreResults.forEach(result -> print(playerScoreText(result)));
    }

    private static String playerScoreText(PlayerScoreResult result) {
        return result.getNameAsString() + ": " + result.getVictoryAsString();
    }

    private static StringBuilder generateDealerScoreTexts(DealerScoreResult dealerScoreResult) {
        StringBuilder dealerScoresText = new StringBuilder("딜러: ");
        for (Victory victory : Victory.values()) {
            dealerScoresText.append(dealerScoreResult.getVictoryScoreAsString(victory) + " ");
        }
        return dealerScoresText;
    }

    public static void printRetryMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

}
