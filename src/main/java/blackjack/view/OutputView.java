package blackjack.view;

import blackjack.dto.NameCards;
import blackjack.dto.NameCardsScore;
import blackjack.dto.ProfitStatement;
import blackjack.model.deck.Card;
import blackjack.view.display.ScoreDisplay;
import blackjack.view.display.ShapeDisplay;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static final String DEALER_NAME = "딜러";

    public void println() {
        System.out.println();
    }

    public void printDistributionSubject(final List<String> names) {
        String formattedName = String.join(", ", names);
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", DEALER_NAME, formattedName));
    }

    public void printPlayersNamesAndCards(final List<NameCards> nameCards) {
        nameCards.forEach(this::printNameAndCards);
        System.out.println();
    }

    public void printNameAndCards(final NameCards nameCards) {
        System.out.println(nameCards.name() + ": " + convert(nameCards.cards()));
    }

    private String convert(final List<Card> cards) {
        return cards.stream()
                .map(card -> ScoreDisplay.getValue(card.getScore()) + ShapeDisplay.getValue(card.getShape()))
                .collect(Collectors.joining(", "));
    }

    public void printDealerHit() {
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardsAndScore(final List<NameCardsScore> nameCardsScores) {
        nameCardsScores.forEach(this::printFinalCardsAndScore);
    }

    public void printFinalCardsAndScore(final NameCardsScore nameCardsScore) {
        System.out.println(
                nameCardsScore.name() + ": " + convert(nameCardsScore.cards()) + " - 결과: " + nameCardsScore.score());
    }

    public void printDealerProfit(final int profit) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println(DEALER_NAME + ": " + profit);
    }

    public void printPlayersProfit(final List<ProfitStatement> profitStatements) {
        System.out.println(formatPlayersProfit(profitStatements));
    }

    private String formatPlayersProfit(final List<ProfitStatement> profitStatements) {
        return profitStatements.stream()
                .map(this::formatPlayerProfit)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatPlayerProfit(ProfitStatement profitStatement) {
        return profitStatement.name() + ": " + profitStatement.profit();
    }
}
