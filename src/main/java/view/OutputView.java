package view;

import java.util.List;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = " 카드: ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printMessage(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(DealerFaceUpResult dealerResult,
                                               List<PlayerFaceUpResult> playersResult) {
        System.out.print(System.lineSeparator());
        List<String> playerNameTexts = playersResult.stream()
                .map(PlayerFaceUpResult::getPartipantNameAsString)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        printMessage("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResult);
        printPlayersFaceUp(playersResult);
        System.out.print(System.lineSeparator());
    }

    private static void printDealerHoleCard(DealerFaceUpResult faceUpResult) {
        printMessage("딜러" + NAME_CARD_DELIMITER + faceUpResult.getCardsAsStrings()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayersFaceUp(List<PlayerFaceUpResult> playerFaceUpResults) {
        playerFaceUpResults.forEach(OutputView::printSingleFaceUp);
    }

    public static void printFinalFaceUpResult(DealerFaceUpResult dealerFaceUpResult,
                                              List<PlayerFaceUpResult> playerFaceUpResults) {
        System.out.print(System.lineSeparator());
        printSinglePlayerFinalFaceUp(dealerFaceUpResult);
        playerFaceUpResults.forEach(OutputView::printSinglePlayerFinalFaceUp);
    }

    public static void printSingleFaceUp(PlayerFaceUpResult result) {
        printMessage(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()));
    }

    private static void printSinglePlayerFinalFaceUp(DealerFaceUpResult result) {
        printMessage("딜러 " + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER, result.getCardsAsStrings()) + " - 결과: "
                + result.hand());
    }

    private static void printSinglePlayerFinalFaceUp(PlayerFaceUpResult result) {
        printMessage(result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                result.getCardsAsStrings()) + " - 결과: " + result.hand());
    }

    public static void alertDealerHitMessage() {
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerEarningResults(long dealerEarning) {
        System.out.print(System.lineSeparator());
        printMessage("## 최종 승패");
        printMessage("딜러: " + dealerEarning);
    }

    public static void printPlayerBettingResult(String playerName, long playerBettingResult) {
        printMessage(playerName + ": " + playerBettingResult);
    }
}
