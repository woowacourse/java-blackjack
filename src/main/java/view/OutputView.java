package view;

import domain.score.Result;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.StatisticsDto;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String SHOW_CARD = "%s카드: %s";
    private static final String DRAW_DEALER = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String SHOW_RESULT = SHOW_CARD + " - 결과: %d";
    private static final String PRINT_RESULT_PHRASE = "## 최종 승패";
    private static final String DEALER_RECORD_FORMAT = "%s: %s";
    private static final String STATISTICS_FORMAT = "%s: %s";

    private final PrintStream out;

    public OutputView(PrintStream out) {
        this.out = out;
        System.setOut(out);
    }

    public void drawCard(NamesDto namesDto) {
        String namesResult = String.join(", ", namesDto.playerNames());
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n", namesDto.dealerName(), namesResult);
    }

    public void showCard(PlayerCardsDto playerCardsDto) {
        System.out.println(SHOW_CARD.formatted(playerCardsDto.name(), String.join(", ", playerCardsDto.cards())));
    }

    public void showOnlyOneCard(PlayerCardsDto playerCardsDto) {
        System.out.println(
                SHOW_CARD.formatted(playerCardsDto.name(), String.join(", ", playerCardsDto.cards().getFirst())));
    }

    public void showCardsAndScore(PlayerCardsDto playerCardsDto, Integer totalScore) {
        System.out.println(SHOW_RESULT.formatted(playerCardsDto.name(), String.join(", ", playerCardsDto.cards())
                , totalScore));
    }

    public void drawDealer(DealerDrawDto dealerDrawDto) {
        System.out.printf(DRAW_DEALER + "%n", dealerDrawDto.dealerName(), dealerDrawDto.boundary());
    }

    public void showResultStatistics(List<StatisticsDto> statisticsDtos, String dealerName) {
        System.out.println(PRINT_RESULT_PHRASE);
        System.out.println(DEALER_RECORD_FORMAT.formatted(dealerName, makeResult(statisticsDtos)));

        for (StatisticsDto statisticsDto : statisticsDtos) {
            String name = statisticsDto.name();
            String result = statisticsDto.result();
            System.out.println(STATISTICS_FORMAT.formatted(name, result));
        }
    }

    private String makeResult(List<StatisticsDto> statisticsDtos) {
        Map<String, Long> resultCount = statisticsDtos.stream()
                .collect(Collectors.groupingBy(StatisticsDto::result, Collectors.counting()));
        String message = "";

        message = getResultConvertedMessage(resultCount, Result.LOSE, message, Result.WIN);
        message = getResultConvertedMessage(resultCount, Result.DRAW, message, Result.DRAW);
        message = getResultConvertedMessage(resultCount, Result.WIN, message, Result.LOSE);

        return message;
    }

    private static String getResultConvertedMessage(Map<String, Long> resultCount, Result lose, String message,
                                                    Result win) {
        if (resultCount.containsKey(lose.getDisplayName())) {
            message += resultCount.get(lose.getDisplayName()) + win.getDisplayName();
        }
        return message;
    }
}
