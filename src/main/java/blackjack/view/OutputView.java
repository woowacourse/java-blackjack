package blackjack.view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalGameResult;
import java.util.List;

public class OutputView {
    private static final int DEALER_DRAW_BOUNDARY = 16;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartDrawCardMessage(final List<String> playerNames) {
        final String names = String.join(", ", playerNames);
        System.out.println(LINE_SEPARATOR + "딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantStatus(final ParticipantStatusResponse response) {
        System.out.println(getParticipantStatus(response));
    }

    public static void printParticipantTotalStatus(final ParticipantTotalStatusResponse response) {
        System.out.println(getParticipantStatus(response.getParticipantStatusResponse()) + " - 결과: " + response.getScore());
    }

    private static String getParticipantStatus(final ParticipantStatusResponse response) {
        final String allCards = String.join(", ", response.getCards());
        return response.getName() + " 카드: " + allCards;
    }

    public static void printDealerDrawCardMessage(final boolean isDraw) {
        System.out.print(LINE_SEPARATOR);
        if (isDraw) {
            System.out.println("딜러는 " + DEALER_DRAW_BOUNDARY + "이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR);
            return;
        }
        System.out.println("딜러는 " + DEALER_DRAW_BOUNDARY + "초과라 카드를 받지 않았습니다." + LINE_SEPARATOR);
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    public static void printTotalGameResult(TotalGameResult totalGameResult) {
        System.out.println(LINE_SEPARATOR + "## 최종 승패");
        System.out.println("딜러 카드: " + getDealerResult(totalGameResult.getDealerGameResult()));
        for (PlayerGameResult playerGameResult : totalGameResult.getPlayerGameResults()) {
            System.out.println(playerGameResult.getName() + ": " + playerGameResult.getResult().getName());
        }
    }

    private static String getDealerResult(final List<String> dealerResult) {
        return dealerResult.stream()
                .collect(groupingBy(result -> result, counting()))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(joining(" "));
    }
}
