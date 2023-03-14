package view;

import domain.Name;
import domain.ResultAmount;
import dto.CardStatusDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_CARD_DISTRIBUTION = "%s와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_RECEIVE_CARD = "%s는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String NAME_DELIMITER = ", ";
    private static final String FINAL_SCORE = " - 결과: %d";
    private static final String FINAL_RESULT_TITLE = "## 최종 수익";
    private static final String RESULT_NAME_FORMAT = "%s: %d";
    private static final String PARTICIPANT_CARD_STATUS_FORMAT = "%s 카드: %s ";
    private static final int NONE = 0;

    public OutputView() {
    }

    public void printFirstCardDistribution(String dealerName, List<String> playerNames) {
        System.out.printf("\n" + INITIAL_CARD_DISTRIBUTION, dealerName, String.join(NAME_DELIMITER, playerNames));
    }

    public void printCardStatus(String name, List<CardStatusDto> cards) {
        System.out.printf("\n" + PARTICIPANT_CARD_STATUS_FORMAT, name, String.join(NAME_DELIMITER, convertCardStatusToString(cards)));
    }

    private List<String> convertCardStatusToString(List<CardStatusDto> cards) {
        return cards.stream()
                .map(card -> card.getLetterExpression() + card.getShapeName())
                .collect(Collectors.toList());
    }

    public void printDealerMoreCard(String dealerName, int cardCount) {
        if (cardCount != NONE) {
            System.out.printf("\n\n" + DEALER_RECEIVE_CARD + "\n", dealerName, cardCount);
        }
    }

    public void printCardAndScore(String playerName, List<CardStatusDto> cards, int totalScore) {
        printCardStatus(playerName, cards);
        System.out.printf(FINAL_SCORE, totalScore);
    }

    public void printFinalResult(Map<Name, ResultAmount> result) {
        System.out.println("\n\n" + FINAL_RESULT_TITLE);

        result.forEach(this::printEachFinalResult);
    }

    private void printEachFinalResult(Name name, ResultAmount resultAmount) {
        System.out.printf(RESULT_NAME_FORMAT + "\n", name.getValue(), resultAmount.getResultAmount());
    }

}
