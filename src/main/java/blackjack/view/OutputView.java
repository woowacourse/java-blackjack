package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDistributeCardsMessage(List<String> players) {
        String names = String.join(", ", players);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printDealerInitCards(String card) {
        System.out.println("딜러: " + card);
    }

    public void printPlayersInitCards(Map<String, List<String>> initCards) {
        for (String player : initCards.keySet()) {
            System.out.println(player + "카드: " + String.join(", ", initCards.get(player)));
        }
        System.out.println();
    }

    public void printBustMessage() {
        System.out.println("버스트입니다. 카드를 더 받을 수 없습니다.");
    }

    public void printCurrentCards(String playerName, List<String> currentCards) {
        System.out.println(playerName + "카드: " + String.join(", ", currentCards));
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerFinalCards(List<String> cards, int score) {
        System.out.println("\n딜러 카드: " + String.join(", ", cards) + " - 결과: " + score);
    }

    public void printPlayerFinalCards(Map<String, List<String>> cardsWithName, List<Integer> scores) {
        int index = 0;
        for (String playerName : cardsWithName.keySet()) {
            System.out.println(playerName + "카드: " +
                    String.join(", ", cardsWithName.get(playerName))
                    + " - 결과: " + scores.get(index++));
        }
    }

    public void printGameResult(List<Integer> dealerResult, Map<String, String> result) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + dealerResult.get(0) + "승 " + dealerResult.get(1) + "무 " + dealerResult.get(2) + "패 ");
        for (String playerName : result.keySet()) {
            System.out.println(playerName + ": " + result.get(playerName));
        }
    }
}
