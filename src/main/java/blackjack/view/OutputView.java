package blackjack.view;

import blackjack.dto.NameCardsAScore;
import blackjack.model.Card;
import blackjack.model.Result;
import blackjack.view.display.ResultDisplay;
import blackjack.view.display.ScoreDisplay;
import blackjack.view.display.ShapeDisplay;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printDistributionSubject(final List<String> names) {
        String formattedName = String.join(", ", names);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", formattedName));
    }

    public static void printNameAndCards(final String name, final List<Card> cards) {
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

    public static void printFinalCardsAndScore(final NameCardsAScore nameCardsAScore) {
        System.out.println(
                nameCardsAScore.name() + ": " + convert(nameCardsAScore.cards()) + " - 결과: " + nameCardsAScore.score());
    }

    public static void printFinalCardsAndScore(final List<NameCardsAScore> nameCardsAScores) {
        nameCardsAScores.forEach(OutputView::printFinalCardsAndScore);
    }

    public static void printFinalResult(final Map<String, Result> playerResults) {
        playerResults.forEach((name, result) -> System.out.println(formatFinalResult(name, result)));
    }

    private static String formatFinalResult(final String name, final Result result) {
        return name + ": " + ResultDisplay.getValue(result);
    }
}
