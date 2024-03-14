package view;

import java.util.List;
import service.dto.DealerMatchResult;
import service.dto.FaceUpResult;
import service.dto.PlayerMatchResult;
import model.casino.MatchResult;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = " 카드: ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printMessage(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(FaceUpResult dealerResult, List<FaceUpResult> playersResult) {
        System.out.print(System.lineSeparator());
        List<String> playerNameTexts = playersResult.stream()
                .map(FaceUpResult::getPartipantNameAsString)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        printMessage("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResult);
        printPlayersFaceUp(playersResult);
        System.out.print(System.lineSeparator());
    }

    private static void printDealerHoleCard(FaceUpResult faceUpResult) {
        printMessage("딜러" + NAME_CARD_DELIMITER + faceUpResult.getCardsAsStrings()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayersFaceUp(List<FaceUpResult> faceUpResults) {
        faceUpResults.forEach(OutputView::printSingleFaceUp);
    }

    public static void printFinalFaceUpResult(FaceUpResult dealerFaceUpResult, List<FaceUpResult> playerFaceUpResults) {
        System.out.print(System.lineSeparator());
        printSinglePlayerFinalFaceUp(dealerFaceUpResult);
        playerFaceUpResults.forEach(OutputView::printSinglePlayerFinalFaceUp);
    }

    public static void printSingleFaceUp(FaceUpResult result) {
        printMessage(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()));
    }

    private static void printSinglePlayerFinalFaceUp(FaceUpResult result) {
        printMessage(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()) + " - 결과: " + result.hand());
    }

    public static void printScoreResults(DealerMatchResult dealerMatchResult,
                                         List<PlayerMatchResult> playerMatchResults) {
        StringBuilder dealerScoresText = genernateDealerScoreTexts(dealerMatchResult);

        System.out.print(System.lineSeparator());
        printMessage("## 최종 승패");
        printMessage(dealerScoresText.toString());
        playerMatchResults.forEach(result -> printMessage(playerScoreText(result)));
    }

    private static String playerScoreText(PlayerMatchResult result) {
        return result.getNameAsString() + ": " + result.getVictoryAsString();
    }

    private static StringBuilder genernateDealerScoreTexts(DealerMatchResult dealerMatchResult) {
        StringBuilder dealerScoresText = new StringBuilder("딜러: ");
        for (MatchResult matchResult : MatchResult.values()) {
            dealerScoresText.append(dealerMatchResult.getVictoryScoreAsString(matchResult) + " ");
        }
        return dealerScoresText;
    }

}
