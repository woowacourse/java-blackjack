package view;

import java.util.List;
import model.dto.IndividualFaceUpResult;

public class OutputView {
    private static final int HOLE_CARD_INDEX = 0;
    public static final String NAME_CARD_DELIMITER = ": ";
    public static final String CARD_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printNewLine() {
        System.out.print(System.lineSeparator());
    }

    public static void print(String toBePrint) {
        System.out.println(toBePrint);
    }

    public static void printInitialCardSetting(IndividualFaceUpResult dealerResult,
                                               List<IndividualFaceUpResult> playersResult) {
        List<String> playerNameTexts = playersResult.stream()
                .map(IndividualFaceUpResult::getPartipantNameAsString)
                .toList();
        String playerNameFormattedText = String.join(CARD_DELIMITER, playerNameTexts);

        print("딜러와 " + playerNameFormattedText + "에게 2장을 나누었습니다.");
        printDealerHoleCard(dealerResult);
        printPlayerFaceUp(playersResult);
    }

    private static void printDealerHoleCard(IndividualFaceUpResult faceUpResult) {
        print(faceUpResult.getPartipantNameAsString() + NAME_CARD_DELIMITER + faceUpResult.getCardsAsStrings()
                .get(HOLE_CARD_INDEX));
    }

    private static void printPlayerFaceUp(List<IndividualFaceUpResult> faceUpResults) {
        faceUpResults.forEach(result -> print(
                result.getPartipantNameAsString() + NAME_CARD_DELIMITER + String.join(CARD_DELIMITER,
                        result.getCardsAsStrings())));
    }
}
