package blackjack.view;

import blackjack.dto.PlayerDto;
import blackjack.dto.ResultDto;

import java.util.List;

public final class OutputView {

    private static final String DELIMITER = ", ";

    public static void printPlayerCards(final PlayerDto playerDto) {
        final String playerCards = convertCardsFormat(playerDto.getCards());
        System.out.printf("%s 카드: %s%n", playerDto.getName(), playerCards);
    }

    public static void printSetupGame(final List<String> names) {
        final String participants = String.join(DELIMITER, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", participants);
    }

    public static void printDealerHit() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayerScore(final PlayerDto playerDto) {
        final String playerCards = convertCardsFormat(playerDto.getCards());
        System.out.printf("%s 카드: %s - 결과: %d%n", playerDto.getName(), playerCards, playerDto.getScore());
    }

    public static void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    public static void printGameResult(final ResultDto result) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        System.out.printf("딜러: %d%n", result.getDealerResult());

        result.getParticipantsResult()
                .forEach((name, revenue) -> System.out.printf("%s: %d%n", name, revenue));
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    private static String convertCardsFormat(final List<String> cards) {
        return String.join(DELIMITER, cards);
    }
}
