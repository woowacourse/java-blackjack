package blackjack.view;

import blackjack.domain.card.Score;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final int NUMBER_OF_SETTING_CARDS = 2;
    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

    public void printDistributeCardsMessage(List<String> players) {
        String names = String.join(DELIMITER, players);
        System.out.println("\n" + DEALER_NAME + "와 " + names + "에게 " + NUMBER_OF_SETTING_CARDS + "장을 나누었습니다.");
    }

    public void printDealerInitCards(List<String> card) {
        System.out.println(DEALER_NAME + ": " + String.join("", card));
    }

    public void printPlayersInitCards(Map<String, List<String>> initCards) {
        for (String player : initCards.keySet()) {
            System.out.println(player + "카드: " + String.join(DELIMITER, initCards.get(player)));
        }
        System.out.println();
    }

    public void printBustMessage() {
        System.out.println("버스트입니다. 카드를 더 받을 수 없습니다.");
    }

    public void printCurrentCards(String playerName, List<String> currentCards) {
        System.out.println(playerName + "카드: " + String.join(DELIMITER, currentCards));
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("\n" + DEALER_NAME + "는 " + SPECIFIC_SCORE_OF_DEALER + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerFinalCards(List<String> cards, Score score) {
        System.out.println("\n" + DEALER_NAME + " 카드: " + String.join(DELIMITER, cards) + " - 결과: " + score.getValue());
    }

    public void printPlayerFinalCards(Map<String, List<String>> cardsWithName, List<Score> scores) {
        int index = 0;
        for (String playerName : cardsWithName.keySet()) {
            System.out.println(playerName + "카드: " +
                    String.join(DELIMITER, cardsWithName.get(playerName))
                    + " - 결과: " + scores.get(index++).getValue());
        }
    }

    public void printProfit(Map<String, Integer> result) {
        System.out.println("\n## 최종 수익");
        for (String playerName : result.keySet()) {
            System.out.println(playerName + ": " + result.get(playerName));
        }
    }
}
