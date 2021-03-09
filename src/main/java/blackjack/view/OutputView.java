package blackjack.view;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import blackjack.domain.GameResult.WinOrLose;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.PlayerDto;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DRAW_RESULT_FORMAT =
        System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String DECK_STATE_FORMAT = "%s 카드: %s" + System.lineSeparator();
    private static final String DEALER_DRAWABLE_MESSAGE =
        System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PLAYER_RESULT_FORMAT =
        "%s 카드 : %s - 결과 : %d" + System.lineSeparator();
    private static final String DELIMITER = ",";
    private static final String FINAL_RESULT = System.lineSeparator() + "##최종승패";
    private static final String DEALER_FINAL_RESULT = "딜러: %d승 %d패";
    private static final String PLAYER_FINAL_RESULT = "%s: %s" + System.lineSeparator();

    public static void printDrawResult(List<PlayerDto> players) {
        System.out.printf(DRAW_RESULT_FORMAT,
            players.stream()
                .map(PlayerDto::getName)
                .collect(joining(DELIMITER)));
        System.out.println();
    }


    public static void printDealerAndPlayersDeckState(PlayerDto dealer,
        List<PlayerDto> gamersDeck) {
        System.out
            .printf(DECK_STATE_FORMAT, dealer.getName(), dealer.getCardList().get(0).getName());

        gamersDeck.forEach(OutputView::printPlayersDeckState);
        System.out.println();
    }

    public static void printPlayersDeckState(PlayerDto player) {
        System.out.printf(DECK_STATE_FORMAT, player.getName(), player.getCardList().stream()
            .map(CardDto::getName).collect(joining(DELIMITER))
        );
    }

    public static void dealerDrawsCard() {
        System.out.println(DEALER_DRAWABLE_MESSAGE);
    }

    public static void printCurrentDeckAndScore(List<PlayerDto> resultDtos) {
        System.out.println();
        for (PlayerDto resultDto : resultDtos) {
            String cards = resultDto.getCardList()
                .stream()
                .map(CardDto::getName)
                .collect(joining(DELIMITER));
            System.out
                .printf(PLAYER_RESULT_FORMAT, resultDto.getName(), cards, resultDto.getScore());
        }
    }

    public static void printResult(Map<WinOrLose, Long> dealerResult,
        Map<String, WinOrLose> gamerResult) {
        System.out.println(FINAL_RESULT);
        System.out.println(dealerResultToString(dealerResult));

        gamerResult.forEach(
            (name, result) -> System.out.printf(PLAYER_FINAL_RESULT, name, result.getMessage()));
    }

    private static String dealerResultToString(Map<WinOrLose, Long> dealerResult) {
        return String.format(
            DEALER_FINAL_RESULT,
            dealerResult.getOrDefault(WinOrLose.WIN, 0L),
            dealerResult.getOrDefault(WinOrLose.LOSE, 0L)
        );
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
