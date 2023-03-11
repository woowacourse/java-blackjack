package view;

import domain.deck.Card;
import domain.player.Name;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistributeCard(final List<Name> names) {
        final String joinedName = names.stream()
                .map(Name::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinedName);
    }

    public static void printDealerCard(final Card card) {
        final String rank = card.rank();
        final String suit = card.suit();
        System.out.printf("딜러: %s%s%n", rank, suit);
    }

    public static void printPlayerCard(final Name name, final List<Card> cards) {
        final String toStringCards = toStringCards(cards);

        System.out.printf("%s: %s%n", name.getName(), toStringCards);
    }

    private static String toStringCards(final List<Card> cards) {
        final StringJoiner stringJoiner = new StringJoiner(", ");
        cards.forEach(card -> {
            final String cardPhrase = card.rank() + card.suit();
            stringJoiner.add(cardPhrase);
        });
        return stringJoiner.toString();
    }

    public static void printOneMoreCard(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name.getName());
    }

    public static void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(final Name name, final List<Card> cards, final int score) {
        System.out.printf("%s 카드: %s - 결과: %s%n", name.getName(), toStringCards(cards), score);
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printInputAmount(final Name name) {
        System.out.printf("%s의 베팅 금액은?%n", name.getName());
    }

    public static void printProfitResult(final Map<Name, Integer> profits) {
        System.out.println("## 최종 수익");
        profits.forEach(OutputView::printProfit);
    }

    private static void printProfit(final Name name, final Integer profit) {
        System.out.printf("%s: %d%n", name.getName(), profit);
    }
}
