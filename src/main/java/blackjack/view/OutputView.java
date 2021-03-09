package blackjack.view;

import blackjack.domain.result.ResultOfDealer;
import blackjack.domain.result.ResultOfGamer;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.PlayerDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

public class OutputView {

    private static final String DRAW_RESULT_FORMAT =
            System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String DECK_STATE_FORMAT = "%s 카드: %s" + System.lineSeparator();
    private static final String DEALER_DRAWABLE_MESSAGE =
            System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PLAYER_RESULT_FORMAT =
            "%s 카드 : %s - 결과 : %d" + System.lineSeparator();
    private static final String DELIMITER = ",";
    private static final String FINAL_WIN_AND_LOSE_RESULT = System.lineSeparator() + "##최종승패";
    private static final String DEALER_FINAL_WIN_AND_LOSE_RESULT = "딜러: %d승 %d패";
    private static final String PLAYER_FINAL_WIN_AND_LOSE_RESULT = "%s: %s" + System.lineSeparator();
    private static final String REVENUE_RESULT = System.lineSeparator() + "##최종 수익";
    private static final String REVENUE_RESULT_FORMAT = "%s: %s" + System.lineSeparator();

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

    public static void printFinalWinAndLoseResult(ResultOfDealer resultOfDealer,
                                                  List<ResultOfGamer> resultOfGamers) {
        System.out.println(FINAL_WIN_AND_LOSE_RESULT);
        System.out.println(dealerResultToString(resultOfDealer.getWinOrLosesAsString()));

        resultOfGamers.forEach(
                result -> System.out.printf(PLAYER_FINAL_WIN_AND_LOSE_RESULT, result.getName(), result.getWinOrLoseAsString()));
    }
    
    public static void printFinalRevenueResult(ResultOfDealer resultOfDealer,
                                               List<ResultOfGamer> resultOfGamers) {
        System.out.println(REVENUE_RESULT);
        System.out.printf(REVENUE_RESULT_FORMAT, "딜러", resultOfDealer.getRevenue());

        resultOfGamers.forEach(
                result -> System.out.printf(REVENUE_RESULT_FORMAT, result.getName(), result.getRevenue()));
    }

    private static String dealerResultToString(List<String> dealerResult) {
        Map<String, Long> map = dealerResult.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        return String.format(
                DEALER_FINAL_WIN_AND_LOSE_RESULT,
                map.getOrDefault("승", 0L),
                map.getOrDefault("패", 0L)
        );
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
