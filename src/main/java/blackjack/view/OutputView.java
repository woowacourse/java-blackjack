package blackjack.view;

import blackjack.dto.ParticipantProfitResponse;
import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerNamesResponse;
import java.util.List;

public class OutputView {
    private static final int DEALER_DRAW_BOUNDARY = 16;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartDrawCardMessage(final PlayerNamesResponse response) {
        final String names = String.join(", ", response.getNames());
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

    public static void printTotalGameResult(List<ParticipantProfitResponse> responses) {
        System.out.println(LINE_SEPARATOR + "## 최종 수익");
        for (ParticipantProfitResponse response : responses) {
            System.out.println(response.getName() + ": " + response.getProfit());
        }
    }
}
