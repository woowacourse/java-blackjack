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
}
