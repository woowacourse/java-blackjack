package view;

import domain.enums.Result;
import dto.CardDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = "\n";
    private static final String STRING_JOIN_DELIMITER = ", ";
    private static final String DISTRIBUTE_INITIAL_CARD = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD = "딜러카드: %s";
    private static final String PLAYER_CARD = "%s카드: %s";
    private static final String SCORE = " - 결과: %d";
    private static final String DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String DEALER_RESULT = "딜러: %d승 %d패 %d무";
    private static final String PLAYER_RESULT = "%s: %s";

    private OutputView() {
    }

    public static void printParticipantCards(List<CardDto> dealerCards, Map<String, List<CardDto>> playerCards) {
        printDistributeComplete(playerCards.keySet().stream().toList());
        printDealerFirstCard(dealerCards);

        for (Map.Entry<String, List<CardDto>> entry : playerCards.entrySet()) {
            printPlayerCards(entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    private static void printDistributeComplete(List<String> playerNames) {
        System.out.printf(LINE_SEPARATOR + DISTRIBUTE_INITIAL_CARD + LINE_SEPARATOR,
                String.join(STRING_JOIN_DELIMITER, playerNames));
    }

    private static void printDealerFirstCard(List<CardDto> dealerCards) {
        CardDto firstCard = dealerCards.getFirst();
        System.out.printf(DEALER_CARD + LINE_SEPARATOR, firstCard.rank().getRank() + firstCard.suit().getSuit());
    }

    public static void printPlayerCards(String name, List<CardDto> playerCards) {
        String card = collectCards(playerCards);

        System.out.printf(PLAYER_CARD + LINE_SEPARATOR, name, card);
    }

    private static String collectCards(List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.rank().getRank() + cardDto.suit().getSuit())
                .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    public static void printDealerCardsWithScore(List<CardDto> dealerCard, int score) {
        String card = collectCards(dealerCard);

        System.out.printf(LINE_SEPARATOR + DEALER_CARD + SCORE + LINE_SEPARATOR, card, score);
    }

    public static void printPlayerCardsWithScore(String name, List<CardDto> playerCards, int score) {
        String card = collectCards(playerCards);

        System.out.printf(PLAYER_CARD + SCORE + LINE_SEPARATOR, name, card, score);
    }

    public static void printDealerHit() {
        System.out.println(LINE_SEPARATOR + DEALER_DRAW);
    }

    public static void printGameResult(Map<Result, Integer> dealerResult, Map<String, Result> playerResults) {
        System.out.println(LINE_SEPARATOR + FINAL_RESULT);

        int winCount = dealerResult.getOrDefault(Result.WIN, 0);
        int loseCount = dealerResult.getOrDefault(Result.LOSE, 0);
        int drawCount = dealerResult.getOrDefault(Result.DRAW, 0);

        System.out.printf(DEALER_RESULT + LINE_SEPARATOR, winCount, loseCount, drawCount);
        for (String playerName : playerResults.keySet()) {
            System.out.printf(PLAYER_RESULT + LINE_SEPARATOR, playerName,
                    playerResults.get(playerName).getDescription());
        }
    }
}
