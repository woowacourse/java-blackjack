package blackjack.view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import blackjack.domain.GameResult;
import blackjack.dto.DealerGameResultResponse;
import blackjack.dto.PersonStatusResponse;
import blackjack.dto.PersonTotalStatusResponse;
import blackjack.dto.PlayerGameResultResponse;
import java.util.List;

public class OutputView {
    private static final int DEALER_DRAW_BOUNDARY = 16;

    private OutputView() {
    }

    public static void printDefaultDrawCardMessage(final List<String> playerNames) {
        final String names = String.join(", ", playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printPersonStatus(final PersonStatusResponse response) {
        System.out.println(getCardStatus(response));
    }

    public static void printPersonTotalStatus(final PersonTotalStatusResponse response) {
        System.out.println(getCardStatus(response) + " - 결과: " + response.getScore());
    }

    private static String getCardStatus(final PersonStatusResponse response) {
        final String allCards = String.join(", ", response.getCards());
        return response.getName() + " 카드: " + allCards;
    }

    public static void printDealerDrawCardMessage(final boolean isDraw) {
        if (isDraw) {
            System.out.println("\n딜러는 " + DEALER_DRAW_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.\n");
            return;
        }
        System.out.println("\n딜러는 " + DEALER_DRAW_BOUNDARY + "초과라 카드를 받지 않았습니다.\n");
    }

    public static void printGameEndMessage() {
        System.out.println("\n## 최종 승패");
    }

    public static void printDealerResult(final DealerGameResultResponse response) {
        final String result = response.getResults().stream()
                .collect(groupingBy(GameResult::getName, counting()))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(joining(" "));
        System.out.println("딜러: " + result);
    }

    public static void printPlayerResult(final PlayerGameResultResponse response) {
        System.out.println(response.getName() + ": " + response.getGameResult());
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
