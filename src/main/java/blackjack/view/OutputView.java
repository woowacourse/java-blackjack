package blackjack.view;

import blackjack.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printDealAnnounce(List<String> names) {
        String nameFormat = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", nameFormat);
    }

    public static void printDealCards(String name, List<Card> cards) {
        String cardFormat = cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));

        System.out.printf("%s카드 : %s%n", name, cardFormat);
    }

    public static void printDealCard(String name, Card card) {
        System.out.printf("%s : %s%n", name, card);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printDealerHitAnnounce() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
