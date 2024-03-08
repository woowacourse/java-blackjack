package view;

import java.util.List;
import model.dto.DealerScoreResult;
import model.dto.FaceUpResult;
import model.dto.PlayerScoreResult;
import model.dto.Victory;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = " 카드: ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void print(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(FaceUpResult dealerResult, List<FaceUpResult> playersResult) {
        System.out.print(System.lineSeparator());
        List<String> playerNameTexts = playersResult.stream()
                .map(FaceUpResult::getPartipantNameAsString)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        print("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResult);
        printPlayersFaceUp(playersResult);
        System.out.print(System.lineSeparator());
    }

    private static void printDealerHoleCard(FaceUpResult faceUpResult) {
        print(faceUpResult.getPartipantNameAsString() + NAME_CARD_DELIMITER + faceUpResult.getCardsAsStrings()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayersFaceUp(List<FaceUpResult> faceUpResults) {
        faceUpResults.forEach(OutputView::printSinglePlayerFaceUp);
    }

    public static void printSinglePlayerFaceUp(FaceUpResult result) {
        print(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()));
    }

    public static void printFinalFaceUpResult(FaceUpResult dealerFaceUpResult, List<FaceUpResult> playerFaceUpResults) {
        System.out.print(System.lineSeparator());
        printSinglePlayerFinalFaceUp(dealerFaceUpResult);
        playerFaceUpResults.forEach(OutputView::printSinglePlayerFinalFaceUp);
    }

    private static void printSinglePlayerFinalFaceUp(FaceUpResult result) {
        print(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()) + " - 결과: " + result.hand());
    }

    public static void printScoreResults(DealerScoreResult dealerScoreResult,
                                         List<PlayerScoreResult> playerScoreResults) {
        StringBuilder dealerScoresText = genernateDealerScoreTexts(dealerScoreResult);

        System.out.print(System.lineSeparator());
        print("## 최종 승패");
        print(dealerScoresText.toString());
        playerScoreResults.forEach(result -> print(playerScoreText(result)));
    }

    private static String playerScoreText(PlayerScoreResult result) {
        return result.getNameAsString() + ": " + result.getVictoryAsString();
    }

    private static StringBuilder genernateDealerScoreTexts(DealerScoreResult dealerScoreResult) {
        StringBuilder dealerScoresText = new StringBuilder("딜러: ");
        for (Victory victory : Victory.values()) {
            dealerScoresText.append(dealerScoreResult.getVictoryScoreAsString(victory) + " ");
        }
        return dealerScoresText;
    }

}
