package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.gamer.Dealer.HIT_UPPER_BOUND;
import static blackjack.domain.gamer.Gamer.DEAL_CARD_COUNT;

public class OutputView {
    static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DELIMITER = ", ";
    private static final String DEAL_ANNOUNCE_FORMAT = String.format("딜러와 %%s에게 %d장을 나누었습니다.", DEAL_CARD_COUNT);
    private static final String DEALER_HIT_FORMAT = String.format("딜러는 %d 이하라 한장의 카드를 더 받았습니다.", HIT_UPPER_BOUND);
    private static final String CARD_FORMAT = "%s 카드 : %s";
    private static final String RESULT_SCORES_FORMAT = " - 결과: %d";
    private static final String WIN_STATUS_FORMAT = "%s: %s";
    private static final String WIN_ANNOUNCE = "## 최종 승패";

    public static void printDealAnnounce(List<String> names) {
        String nameFormat = String.join(DELIMITER, names);
        System.out.printf(LINE_SEPARATOR + DEAL_ANNOUNCE_FORMAT + LINE_SEPARATOR, nameFormat);
    }

    public static void printDealCards(String name, List<Card> cards) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCards(cards));
    }

    public static void printDealCard(String name, Card card) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCard(card));
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(DELIMITER));
    }

    private static String formatCard(Card card) {
        return CardScoreName.convert(card.getScore()) + CardSymbolName.convert(card.getSymbol());
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printDealerHitAnnounce() {
        System.out.println(DEALER_HIT_FORMAT);
    }

    public static void printGamerCards(String name, List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, name, formatCards(cards), totalScore);
    }

    public static void printWinAnnounce() {
        System.out.println(LINE_SEPARATOR + WIN_ANNOUNCE);
    }

    public static void printDealerWinStatus(String name, Map<GameResult, Integer> gameResults) {
        String gameResultsFormat = gameResults.keySet().stream()
                .map(key -> gameResults.get(key) + GameResultName.convert(key))
                .collect(Collectors.joining(" "));
        System.out.printf(WIN_STATUS_FORMAT + LINE_SEPARATOR, name, gameResultsFormat);
    }

    public static void printPlayerWinStatus(String name, GameResult gameResult) {
        System.out.printf(WIN_STATUS_FORMAT + LINE_SEPARATOR, name, GameResultName.convert(gameResult));
    }
}
