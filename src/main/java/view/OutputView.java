package view;

import dto.CardStatusDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_CARD_DISTRIBUTION = "%s와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_RECEIVE_CARD = "%s는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String NAME_DELIMITER = ", ";
    private static final String FINAL_SCORE = " - 결과: %d";
    private static final String FINAL_REVENUE_TITLE = "## 최종 수익";
    private static final String PARTICIPANT_CARD_STATUS_FORMAT = "%s 카드: %s ";
    private static final String REVENUE_FORMAT = "%s: %d";
    private static final int NONE = 0;

    public OutputView() {
    }

    public void printFirstCardDistribution(String dealerName, List<String> playerNames) {
        System.out.printf("\n" + INITIAL_CARD_DISTRIBUTION, dealerName, String.join(NAME_DELIMITER, playerNames));
    }

    public void printAllParticipantsInitialCard(List<String> names, List<List<CardStatusDto>> cards) {
        for (int index = 0; index < names.size(); index++) {
            printCardStatus(names.get(index), cards.get(index));
        }
    }

    public void printCardStatus(String name, List<CardStatusDto> cardStatuses) {
        System.out.printf("\n" + PARTICIPANT_CARD_STATUS_FORMAT, name,
                String.join(NAME_DELIMITER, convertCardStatus(cardStatuses)));
    }

    private List<String> convertCardStatus(List<CardStatusDto> cardStatuses) {
        return cardStatuses.stream()
                .map(cardStatus -> cardStatus.getLetter() + cardStatus.getShape())
                .collect(Collectors.toList());
    }

    public void printDealerMoreCard(String dealerName, int cardCount) {
        if (cardCount != NONE) {
            System.out.printf("\n\n" + DEALER_RECEIVE_CARD + "\n", dealerName, cardCount);
        }
        System.out.println();
    }

    public void printAllParticipantsCardAndScore(List<String> names, List<List<CardStatusDto>> cards,
                                                 List<Integer> scores) {
        for (int index = 0; index < names.size(); index++) {
            printCardAndScore(names.get(index), cards.get(index), scores.get(index));
        }
    }

    private void printCardAndScore(String playerName, List<CardStatusDto> cardStatuses, int totalScore) {
        printCardStatus(playerName, cardStatuses);
        System.out.printf(FINAL_SCORE, totalScore);
    }

    public void printFinalResult(Map<String, Double> results) {
        System.out.println("\n\n" + FINAL_REVENUE_TITLE);
        for (Entry<String, Double> result : results.entrySet()) {
            String playerRevenue = String.format(REVENUE_FORMAT, result.getKey(), result.getValue().intValue());
            System.out.println(playerRevenue);
        }
    }

}
