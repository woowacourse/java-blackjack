package blackjack.view;

import blackjack.dto.NameCardsScore;
import blackjack.model.betting.Money;
import blackjack.model.deck.Card;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
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

    public static void printDistributionSubject(final List<Name> names) {
        String formattedName = names.stream()
                .map(Name::getRawName)
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", formattedName);
    }

    public static void printNameAndCards(final Name name, final List<Card> cards) {
        System.out.println(name.getRawName() + ": " + convert(cards));
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
                nameCardsScore.name().getRawName() + ": " + convert(nameCardsScore.cards()) + " - 결과: " + nameCardsScore.score());
    }

    public static void printDealerProfit(final Money dealerProfit) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealerProfit.getValue());
    }

    // TODO: DTO etc
    public static void printPlayerProfit(final Map<Player, Money> playerProfits) {
        playerProfits.forEach((player, profitMoney) -> System.out.println(formatFinalResult(player.getName(), profitMoney)));
    }

    private static String formatFinalResult(final Name name, final Money money) {
        return name.getRawName() + ": " + money.getValue();
    }
}
