package view;

import domain.BettingResult;
import domain.Card;
import domain.Money;
import domain.Name;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###########");

    public static void printInitialStep(List<Name> names) {
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.%n", buildGamerNames(names));
    }

    private static String buildGamerNames(List<Name> names) {
        return names.stream()
                .map(Name::name)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerInitialCards(List<Card> cards) {
        System.out.printf("딜러: %s%n", buildDealerCards(cards));
    }

    private static String buildDealerCards(List<Card> cards) {
        return buildCardString(cards.get(0));
    }

    private static String buildCardString(Card card) {
        return card.getCardNumber().getSymbol() + card.getCardType().getType();
    }

    public static void printPlayerCards(Name name, List<Card> cards) {
        System.out.println(buildNameCards(name.name(), cards));
    }

    public static void printNewLine() {
        System.out.println();
    }

    private static String buildNameCards(String name, List<Card> cards) {
        String cardString = cards.stream()
                .map(OutputView::buildCardString)
                .collect(Collectors.joining(DELIMITER));
        return "%s카드: %s".formatted(name, cardString);
    }

    private static String buildCardsString(List<Card> cards) {
        return cards.stream().map(OutputView::buildCardString).collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerHitCount(int dealerDrawCount) {
        System.out.println();
        System.out.print("딜러는 16이하라 한장의 카드를 더 받았습니다.\n".repeat(dealerDrawCount));
        System.out.println();
    }

    public static void printDealerStatus(List<Card> dealerCards, int dealerScore) {
        System.out.printf("딜러 카드: %s - %s%n", buildCardsString(dealerCards), buildTotalScore(dealerScore));
    }

    public static void printPlayerStatus(Name playerName, List<Card> cards, int playerScore) {
        System.out.printf("%s - %s%n", buildNameCards(playerName.name(), cards), buildTotalScore(playerScore));
    }

    private static String buildTotalScore(int totalScore) {
        return "결과: %d".formatted(totalScore);
    }

    public static void printBettingResult(BettingResult gameResult) {
        System.out.println("\n## 최종 수익");
        Money dealerGameResult = gameResult.getDealerResult();
        Map<Name, Money> playersGameResult = gameResult.getPlayersResult();

        System.out.printf("딜러: %s%n", MONEY_FORMAT.format(dealerGameResult.getAmount()));
        playersGameResult.forEach((name, result) ->
                System.out.printf("%s: %s%n", name.name(), MONEY_FORMAT.format(result.getAmount())));
    }
}
