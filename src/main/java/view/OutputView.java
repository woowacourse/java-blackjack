package view;

import domain.card.Card;
import domain.result.Result;

import java.util.List;

import static java.util.stream.Collectors.*;

public final class OutputView {

    private static final String DELIMITER = ", ";

    public static void printPlayerCards(final String name, final List<Card> cards) {
        final String playerCards = convertCardsFormat(cards);
        System.out.printf("%s 카드: %s%n", name, playerCards);
    }

    public static void printSetupGame(final List<String> names) {
        final String participants = String.join(DELIMITER, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", participants);
    }

    public static void printDealerHit() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n%n");
    }

    public static void printPlayerScore(final String name, final List<Card> cards, final int playerScore) {
        final String playerCards = convertCardsFormat(cards);
        System.out.printf("%s 카드: %s - 결과: %d%n", name, playerCards, playerScore);

    }

    public static void printGameResult(final Result result) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        System.out.printf("딜러: %d승 %d패%n", result.countLosers(), result.countWinners());

        result.getWinners().forEach(winner -> System.out.printf("%s: 승%n", winner));
        result.getLosers().forEach(loser -> System.out.printf("%s: 패%n", loser));
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    private static String convertCardsFormat(final List<Card> cards) {
        return cards.stream().map(card -> String.format("%s%s", card.getNumber().getSymbol(), card.getShape()))
                .collect(joining(DELIMITER));
    }
}
