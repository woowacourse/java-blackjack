package blackjack.view;

import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final int NUMBER_OF_SETTING_CARDS = 2;
    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

    public void printDistributeCardsMessage(final List<String> players) {
        String names = String.join(DELIMITER, players);
        System.out.println("\n" + DEALER_NAME + "와 " + names + "에게 " + NUMBER_OF_SETTING_CARDS + "장을 나누었습니다.");
    }

    public void printParticipantsInitCards(final Map<String, List<String>> initCards) {
        for (String name : initCards.keySet()) {
            System.out.println(name + "카드: " + String.join(DELIMITER, initCards.get(name)));
        }
        System.out.println();
    }

    public void printBustMessage() {
        System.out.println("버스트입니다. 카드를 더 받을 수 없습니다.");
    }

    public void printCurrentCards(final String playerName, final List<String> currentCards) {
        System.out.println(playerName + "카드: " + String.join(DELIMITER, currentCards));
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("\n" + DEALER_NAME + "는 " + SPECIFIC_SCORE_OF_DEALER + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantsScore(final Map<String, List<String>> cardsWithName, final List<Integer> scores) {
        int index = 0;
        for (String name : cardsWithName.keySet()) {
            System.out.println(name + "카드: " +
                    String.join(DELIMITER, cardsWithName.get(name))
                    + " - 결과: " + scores.get(index++));
        }
    }

    public void printProfit(final Map<String, Integer> result) {
        System.out.println("\n## 최종 수익");
        for (String playerName : result.keySet()) {
            System.out.println(playerName + ": " + result.get(playerName));
        }
    }
}
