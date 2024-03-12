package blackjack.view;

import blackjack.dto.DealerResultCount;
import blackjack.dto.NameCards;
import blackjack.dto.NameCardsScore;
import blackjack.dto.PlayerNameFinalResult;
import blackjack.model.deck.Card;
import blackjack.model.result.ResultCommand;
import blackjack.view.display.deck.ScoreDisplay;
import blackjack.view.display.deck.ShapeDisplay;
import blackjack.view.display.result.ResultCommandDisplay;
import java.util.List;
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
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", formattedName));
    }

    public static void printPlayersNamesAndCards(final List<NameCards> nameCards) {
        nameCards.forEach(OutputView::printNameAndCards);
        System.out.println();
    }

    public static void printNameAndCards(final NameCards nameCards) {
        System.out.println(nameCards.name() + ": " + convert(nameCards.cards()));
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

    public static void printDealerFinalResult(final List<DealerResultCount> dealerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + formatFinalResult(dealerResults));
    }

    private static String formatFinalResult(final List<DealerResultCount> dealerResults) {
        return dealerResults.stream()
                .map(dealerResult -> dealerResult.count() + ResultCommandDisplay.getValue(dealerResult.result()))
                .collect(Collectors.joining(" "));
    }

    public static void printFinalResults(final List<PlayerNameFinalResult> playerNameFinalResults) {
        playerNameFinalResults.forEach(OutputView::printFinalResult);
    }

    private static void printFinalResult(final PlayerNameFinalResult playerNameFinalResult) {
        System.out.println(formatFinalResult(playerNameFinalResult));
    }

    private static String formatFinalResult(final PlayerNameFinalResult playerNameFinalResult) {
        return playerNameFinalResult.name() + ": " + ResultCommandDisplay.getValue(playerNameFinalResult.result());
    }
}
