package blackjack.view;

import blackjack.model.Card;
import blackjack.view.display.ScoreDisplay;
import blackjack.view.display.ShapeDisplay;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printDistributionSubject(final List<String> names) {
        String formattedName = String.join(", ", names);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", formattedName));
    }

    public static void printInitialCards(final String name, final List<Card> cards) {
        System.out.println(name + ": " + convert(cards));
    }

    private static String convert(final List<Card> cards) {
        return cards.stream()
                .map(card -> ScoreDisplay.getValue(card.getScore()) + ShapeDisplay.getValue(card.getShape()))
                .collect(Collectors.joining(", "));
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
