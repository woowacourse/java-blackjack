package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printDealAnnounce(List<String> names) {
        String nameFormat = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", nameFormat);
    }

    public static void printDealCards(String name, List<Card> cards) {
        System.out.printf("%s카드 : %s%n", name, formatCards(cards));
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
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

    public static void printGamerCards(String name, List<Card> cards, int totalScore) {
        System.out.printf("%s 카드: %s - 결과: %d%n", name, formatCards(cards), totalScore);
    }

    public static void printWinAnnounce() {
        System.out.println("## 최종 승패");
    }

    public static void printDealerWinStatus(String name, Map<GameResult, Integer> gameResults) {
        String gameResultsFormat = gameResults.keySet().stream()
                .map(key -> gameResults.get(key) + key.get())
                .collect(Collectors.joining(" "));
        System.out.printf("%s: %s%n", name, gameResultsFormat);
    }

    public static void printPlayerWinStatus(String name, GameResult gameResult) {
        System.out.printf("%s: %s%n", name, gameResult.get());
    }
}
