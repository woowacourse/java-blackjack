package view;

import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.PlayersCardsDto;
import dto.StatisticsDto;
import java.io.PrintStream;
import java.util.List;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String SHOW_CARD = "%s카드: %s";
    private static final String DRAW_DEALER = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String SHOW_RESULT = SHOW_CARD + " - 결과: %d";
    private static final String PRINT_RESULT_PHRASE = "## 최종 승패";
    private static final String DEALER_RECORD_FORMAT = "%s: %d";
    private static final String STATISTICS_FORMAT = "%s: %d";

    public OutputView(PrintStream out) {
        System.setOut(out);
    }

    public void drawCard(NamesDto namesDto) {
        String namesResult = String.join(", ", namesDto.playerNames());
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n", namesDto.dealerName(), namesResult);
    }

    public void showCards(PlayerCardsDto playerCardsDto) {
        System.out.printf(SHOW_CARD + "%n", playerCardsDto.name(), String.join(", ", playerCardsDto.cards()));
    }

    public void showPlayersCards(PlayersCardsDto playersCardsDto) {
        playersCardsDto.playerCards().forEach(this::showCards);
    }

    public void showOnlyOneCard(PlayerCardsDto playerCardsDto) {
        System.out.printf(SHOW_CARD + "%n", playerCardsDto.name(),
                String.join(", ", playerCardsDto.cards().getFirst()));
    }

    public void showCardsAndScore(PlayerCardsDto playerCardsDto) {
        System.out.printf(SHOW_RESULT + "%n", playerCardsDto.name(), String.join(", ", playerCardsDto.cards())
                , playerCardsDto.totalScore());
    }

    public void drawDealer(DealerDrawDto dealerDrawDto) {
        System.out.printf(DRAW_DEALER + "%n", dealerDrawDto.dealerName(), dealerDrawDto.boundary());
    }

    public void showResultStatistics(List<StatisticsDto> statisticsDtos, String dealerName) {
        System.out.println(PRINT_RESULT_PHRASE);
        System.out.printf(DEALER_RECORD_FORMAT + "%n", dealerName, makeResult(statisticsDtos));

        for (StatisticsDto statisticsDto : statisticsDtos) {
            String name = statisticsDto.name();
            Integer profit = statisticsDto.profit();
            System.out.printf(STATISTICS_FORMAT + "%n", name, profit);
        }
    }

    private Integer makeResult(List<StatisticsDto> statisticsDtos) {
        return -statisticsDtos.stream()
                .map(StatisticsDto::profit)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
