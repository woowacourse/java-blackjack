package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printDistributeCardsMessage(List<String> players) {
        String names = players.stream()
                .collect(Collectors.joining(", "));
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printDealerInitCards(String card) {
        System.out.println("딜러: " + card);
    }

    public void printPlayersInitCards(Map<String, List<String>> initCards) {
        for (String player : initCards.keySet()) {
            System.out.println(player + "카드: " + initCards.get(player)
                    .stream()
                    .collect(Collectors.joining(", ")));
        }
    }

    public void printBustMessage() {
        System.out.println("버스트입니다. 카드를 더 받을 수 없습니다.");
    }

    public void printCurrentCards(String playerName, List<String> currentCards) {
        System.out.println(playerName + "카드: " + currentCards
                .stream()
                .collect(Collectors.joining(", ")));
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerFinalCards(List<String> cards, int score) {
        System.out.println("딜러 카드: " + cards.stream()
                .collect(Collectors.joining(", ")) + " - 결과: " + score);
    }

    public void printPlayerFinalCards(Map<String, List<String>> cardsWithName, List<Integer> scores) {
        int index = 0;
        for (String playerName : cardsWithName.keySet()) {
            System.out.println(playerName + "카드: " + cardsWithName.get(playerName)
                    .stream()
                    .collect(Collectors.joining(", ")) + " - 결과: " + scores.get(index++));
        }
    }

    public void printGameResult(List<Integer> dealerResult, Map<String, String> result) {
        System.out.println("딜러: " + dealerResult.get(0) + "승 " + dealerResult.get(1) + "무 " + dealerResult.get(2) + "패 ");
        for (String playerName : result.keySet()) {
            System.out.println(playerName + ":" + result.get(playerName));
        }
    }
}
