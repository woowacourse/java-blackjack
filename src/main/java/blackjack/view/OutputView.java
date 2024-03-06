package blackjack.view;

import blackjack.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printDealAnnounce(List<String> names) {
        String nameFormat = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", nameFormat);
    }

    public static void printDealCards(String name, List<Card> cards) {
        String cardFormat = cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));

        System.out.printf("%s : %s%n", name, cardFormat);
    }
}
