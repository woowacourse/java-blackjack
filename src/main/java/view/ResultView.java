package view;

import java.util.List;
import java.util.Map;

import util.Constants;

public class ResultView {

    public static final String JOIN_DELIMITER = ", ";
    private static final int OPEN_CARD_INDEX = 0;
    private static final String FINAL_PROFIT_MESSAGE = "\n## 최종 수익";

    public static void printInitMessage(List<String> playerNames) {
        String names = String.join(JOIN_DELIMITER, playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantResult(String participantName, List<String> cardNames) {
        if (!participantName.equals(Constants.DEALER_NAME)) {
            System.out.println(participantName + "카드: " + String.join(JOIN_DELIMITER, cardNames));
        }
        if (participantName.equals(Constants.DEALER_NAME)) {
            System.out.println(participantName + ": " + cardNames.get(OPEN_CARD_INDEX));
        }
    }

    public static void printParticipantFinalResult(String name, List<String> cardName, int totalValueSum) {
        System.out.println(name + "카드: " + String.join(JOIN_DELIMITER, cardName) + " - 결과: " + totalValueSum);
    }

    public static void printFinalProfit(Double dealerFinalProfit, Map<String, Double> finalProfitByPlayer) {
        System.out.println(FINAL_PROFIT_MESSAGE);
        System.out.println(Constants.DEALER_NAME + ": " + dealerFinalProfit);
        for (String participantName : finalProfitByPlayer.keySet()) {
            System.out.println(participantName + ": " + finalProfitByPlayer.get(participantName));
        }
    }
}
