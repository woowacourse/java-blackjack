package view;

import domain.Card;
import domain.Player;
import domain.Rank;
import domain.Result;
import domain.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final Map<Suit, String> SUIT_NAME = Map.of(
            Suit.HEART, "하트",
            Suit.DIAMOND, "다이아몬드",
            Suit.SPADE, "스페이드",
            Suit.CLUB, "클럽"
    );

    private static final Map<Rank, String> RANK_NAME = Map.of(
            Rank.ACE, "A",
            Rank.JACK, "J",
            Rank.QUEEN, "Q",
            Rank.KING, "K"
    );

    private static final Map<Result, String> RESULT_NAME = Map.of(
            Result.WIN, "승",
            Result.LOSE, "패",
            Result.TIE, "무"
    );

    public void printInitialDeal(List<String> playerNames) {
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printDealerInitialCard(Card card) {
        System.out.println("딜러카드: " + formatCard(card));
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getName() + "카드: " + formatCards(player.getCards()));
    }

    public void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(Player player) {
        System.out.println(player.getName() + "카드: " + formatCards(player.getCards())
                + " - 결과: " + player.calculateScore());
    }

    public void printFinalResult(Player dealer, Map<Player, Result> results) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer, results);
        printPlayerResults(results);
    }

    private void printDealerResult(Player dealer, Map<Player, Result> results) {
        int dealerWin = countResult(results, Result.LOSE);
        int dealerLose = countResult(results, Result.WIN);
        System.out.println(dealer.getName() + ": " + dealerWin + "승 " + dealerLose + "패");
    }

    private int countResult(Map<Player, Result> results, Result target) {
        int count = 0;
        for (Result result : results.values()) {
            if (result == target) {
                count++;
            }
        }
        return count;
    }

    private void printPlayerResults(Map<Player, Result> results) {
        for (Map.Entry<Player, Result> entry : results.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + RESULT_NAME.get(entry.getValue()));
        }
    }

    private String formatCards(List<Card> cards) {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(formatCard(card));
        }
        return String.join(", ", cardNames);
    }

    private String formatCard(Card card) {
        return RANK_NAME.getOrDefault(card.getRank(), String.valueOf(card.getRank().getScore()))
                + SUIT_NAME.get(card.getSuit());
    }
}
