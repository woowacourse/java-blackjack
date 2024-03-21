package view;

import controller.dto.DealerFaceUpResultInfo;
import controller.dto.PlayerFaceUpResultInfo;
import java.util.List;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = " 카드: ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printMessage(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(DealerFaceUpResultInfo dealerResultInfo,
                                               List<PlayerFaceUpResultInfo> playersResultInfo) {
        System.out.print(System.lineSeparator());
        List<String> playerNameTexts = playersResultInfo.stream()
                .map(PlayerFaceUpResultInfo::name)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        printMessage("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResultInfo);
        printPlayersFaceUp(playersResultInfo);
        System.out.print(System.lineSeparator());
    }

    private static void printDealerHoleCard(DealerFaceUpResultInfo faceUpResult) {
        printMessage("딜러" + NAME_CARD_DELIMITER + faceUpResult.cardFaces()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayersFaceUp(List<PlayerFaceUpResultInfo> playerFaceUpResults) {
        playerFaceUpResults.forEach(OutputView::printSingleFaceUp);
    }

    public static void printFinalFaceUpResult(DealerFaceUpResultInfo dealerFaceUpResultInfo,
                                              List<PlayerFaceUpResultInfo> playerFaceUpResultInfos) {
        System.out.print(System.lineSeparator());
        printParticipantFinalFaceUp(dealerFaceUpResultInfo);
        playerFaceUpResultInfos.forEach(OutputView::printParticipantFinalFaceUp);
    }

    public static void printSingleFaceUp(PlayerFaceUpResultInfo result) {
        printMessage(result.name() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER, result.cardFaces()));
    }

    private static void printParticipantFinalFaceUp(DealerFaceUpResultInfo result) {
        printMessage("딜러 " + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER, result.cardFaces()) + " - 결과: "
                + result.hand());
    }

    private static void printParticipantFinalFaceUp(PlayerFaceUpResultInfo result) {
        printMessage(result.name() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER, result.cardFaces()) + " - 결과: "
                + result.hand());
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
