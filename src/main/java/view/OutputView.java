package view;

import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Player;
import domain.card.Rank;
import domain.game.Result;
import domain.card.Suit;
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

    public void printFinalCards(Participant participant) {
        System.out.println(participant.getName() + "카드: " + formatCards(participant.getCards())
                + " - 결과: " + participant.getScore());
    }

    public void printFinalResult(String dealerName, Map<Result, Integer> dealerResult,
                                  Map<Player, Result> playerResults) {
        System.out.println("\n## 최종 승패");
        System.out.println(dealerName + ": "
                + dealerResult.get(Result.WIN) + "승 "
                + dealerResult.get(Result.LOSE) + "패");
        for (Map.Entry<Player, Result> entry : playerResults.entrySet()) {
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
