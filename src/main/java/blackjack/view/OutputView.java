package blackjack.view;

import blackjack.dto.NameCardsScore;
import blackjack.model.deck.Card;
import blackjack.model.result.ResultCommand;
import blackjack.view.display.result.ResultCommandDisplay;
import blackjack.view.display.deck.ScoreDisplay;
import blackjack.view.display.deck.ShapeDisplay;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
        throw new AssertionError("인스턴스를 생성할 수 없습니다.");
    }

    public static void println() {
        System.out.println();
    }

    public static void printDistributionSubject(final List<String> names) {
        String formattedName = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", formattedName);
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

    public static void printFinalCardsAndScore(final List<NameCardsScore> nameCardsScores) {
        nameCardsScores.forEach(OutputView::printFinalCardsAndScore);
    }

    public static void printFinalCardsAndScore(final NameCardsScore nameCardsScore) {
        System.out.println(
                nameCardsScore.name() + ": " + convert(nameCardsScore.cards()) + " - 결과: " + nameCardsScore.score());
    }

    public static void printDealerFinalResult(final Map<ResultCommand, Integer> dealerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + formatFinalResult(dealerResults));
    }

    private static String formatFinalResult(final Map<ResultCommand, Integer> dealerResults) {
        return dealerResults.entrySet().stream()
                .map(resultIntegerEntry -> resultIntegerEntry.getValue() + ResultCommandDisplay.getValue(
                        resultIntegerEntry.getKey()))
                .collect(Collectors.joining(" "));
    }

    public static void printFinalResult(final Map<String, ResultCommand> playerResults) {
        playerResults.forEach((name, result) -> System.out.println(formatFinalResult(name, result)));
    }

    private static String formatFinalResult(final String name, final ResultCommand result) {
        return name + ": " + ResultCommandDisplay.getValue(result);
    }
}
