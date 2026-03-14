package view;

import static domain.constant.GameRule.INIT_CARD_COUNT;
import static view.constant.ViewRule.LINE_SEPARATOR;

import dto.CardDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String STRING_JOIN_DELIMITER = ", ";
    private static final String DISTRIBUTE_INITIAL_CARD = "딜러와 %s에게 %s장을 나누었습니다.";
    private static final String DEALER_CARD = "딜러카드: %s";
    private static final String PLAYER_CARD = "%s카드: %s";
    private static final String SCORE = " - 결과: %d";
    private static final String DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT = "## 최종 수익";
    private static final String DEALER_RESULT = "딜러: %d";
    private static final String PLAYER_RESULT = "%s: %s";

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printLineSeparator() {
        System.out.printf(LINE_SEPARATOR);
    }

    public static void printDistributeComplete(List<String> playerNames) {
        System.out.printf(LINE_SEPARATOR + DISTRIBUTE_INITIAL_CARD + LINE_SEPARATOR,
                String.join(STRING_JOIN_DELIMITER, playerNames), INIT_CARD_COUNT);
    }

    public static void printDealerFirstCard(List<CardDto> dealerCards) {
        CardDto firstCard = dealerCards.getFirst();
        System.out.printf(DEALER_CARD + LINE_SEPARATOR, firstCard.rank() + firstCard.suit());
    }

    public static void printPlayerCards(String name, List<CardDto> playerCards) {
        String card = collectCards(playerCards);
        System.out.printf(PLAYER_CARD + LINE_SEPARATOR, name, card);
    }

    private static String collectCards(List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.rank() + cardDto.suit())
                .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    public static void printDealerHit() {
        System.out.println(LINE_SEPARATOR + DEALER_DRAW);
    }

    public static void printDealerCardsWithScore(List<CardDto> dealerCard, int score) {
        String card = collectCards(dealerCard);
        System.out.printf(LINE_SEPARATOR + DEALER_CARD + SCORE + LINE_SEPARATOR, card, score);
    }

    public static void printPlayerCardsWithScore(String name, List<CardDto> playerCards, int score) {
        String card = collectCards(playerCards);
        System.out.printf(PLAYER_CARD + SCORE + LINE_SEPARATOR, name, card, score);
    }

    public static void printGameResult(int dealerResult, Map<String, Integer> playerResults) {
        System.out.println(LINE_SEPARATOR + FINAL_RESULT);

        System.out.printf(DEALER_RESULT + LINE_SEPARATOR, dealerResult);

        for (String playerName : playerResults.keySet()) {
            System.out.printf(PLAYER_RESULT + LINE_SEPARATOR, playerName, playerResults.get(playerName));
        }
    }
}
