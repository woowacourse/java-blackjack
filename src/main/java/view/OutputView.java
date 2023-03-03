package view;

import domain.Card;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistributeCard(List<String> names) {
        final String joinedName = String.join(", ", names);
        System.out.println(System.lineSeparator() + "딜러와 " + joinedName + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(Card card) {
        System.out.println("딜러: " + card.getRank().getRank() + card.getSuit().getSuit());
    }

    public static void printPlayerCard(String playerName, List<Card> cards) {
        String toStringCards = toStringCards(cards);

        System.out.println(playerName + ": " + toStringCards);
    }

    private static String toStringCards(final List<Card> cards) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        cards.forEach(card -> {
            String temp = card.getRank().getRank() + card.getSuit().getSuit();
            stringJoiner.add(temp);
        });
        return stringJoiner.toString();
    }

    public static void printOneMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(String name, List<Card> cards, int score) {
        System.out.println(name + " 카드: " + toStringCards(cards) + " - 결과: " + score);
    }
}
