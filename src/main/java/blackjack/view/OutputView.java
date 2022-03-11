package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitStatus(Dealer dealer, List<Player> players) {
        List<String> names = new ArrayList<>(List.of("딜러"));
        players.stream()
                .map(player -> player.getName())
                .map(names::add);
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장의 카드를 나누었습니다.");

      /*  System.out.println("딜러: ");*/
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeStatusFormat("딜러", dealer.getMyCards()))
                .append("\n");
        players.forEach(player -> stringBuilder.append(makeStatusFormat(player.getName(),player.getMyCards()))
                .append( "\n"));

        System.out.println(stringBuilder.toString());
    }

    private static String makeStatusFormat(String name, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
        return name + ": " + String.join(", ", cardNames);
    }

    public static void printCards(Player person) {
        System.out.println(makeStatusFormat(person.getName(), person.getMyCards()));
    }

    public static void printDealerAdditionalCard(int number) {
        while(number-- != 0) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public static void printCardsAndResult(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(makeStatusFormat("딜러", dealer.getMyCards()) + printScoreResult(dealer.score()));

        for (Player player: players) {
            System.out.println(makeStatusFormat(player.getName(), player.getMyCards())
                    + printScoreResult(player.score()));
        }
    }

    private static String printScoreResult(int score) {
        return (" - 결과: " + score);
    }

    public static void printResult(Map<Player, Integer> result) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + getDealerResult(result));
        Set<Player> keys = result.keySet();
        for (Player key : keys) {
            System.out.print(key.getName() + ": ");
            if (result.get(key) == 1) {
                System.out.println("승");
            } else if (result.get(key) == 0) {
                System.out.println("무");
            } else {
                System.out.println("패");
            }
        }
    }

    private static String getDealerResult(Map<Player, Integer> result) {
        Set<Player> keys = result.keySet();
        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;
        for (Player key : keys) {
            if (result.get(key) == 1) {
                ++winCount;
            } else if (result.get(key) == -1) {
                ++loseCount;
            } else {
                ++drawCount;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (winCount > 0) {
            stringBuilder.append(winCount + "승");
        }
        if (drawCount > 0) {
            stringBuilder.append(drawCount + "무");
        }
        if (loseCount > 0) {
            stringBuilder.append(loseCount + "패");
        }
        return stringBuilder.toString();
    }
}
