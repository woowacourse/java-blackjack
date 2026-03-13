package view;

import dto.DealerDrawDto;
import dto.NamesDto;
import dto.ParticipantsCardsDto;
import dto.StatisticsDto;
import java.util.List;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "\n%s와 %s에게 2장을 나누었습니다.";
    private static final String CONGRATULATE_BLACKJACK = "%s는 블랙잭입니다. 축하합니다!";
    private static final String SHOW_CARD = "%s카드: %s";
    private static final String DRAW_DEALER = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String SHOW_RESULT = SHOW_CARD + " - 결과: %d";
    private static final String PRINT_RESULT_PHRASE = "## 최종 수익";
    private static final String DEALER_RECORD_FORMAT = "%s: %s";
    private static final String STATISTICS_FORMAT = "%s: %s";

    public void drawCard(NamesDto namesDto) {
        String namesResult = String.join(", ", namesDto.playerNames());
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n", namesDto.dealerName(), namesResult);
    }

    public void showCard(ParticipantsCardsDto playerCardsDto) {
        System.out.printf(SHOW_CARD + "%n", playerCardsDto.name(), String.join(", ", playerCardsDto.cards()));
    }

    public void showCardsAndScore(ParticipantsCardsDto playerCardsDto, Integer totalScore) {
        System.out.printf(SHOW_RESULT + "%n",
                playerCardsDto.name(),
                String.join(", ", playerCardsDto.cards()),
                totalScore);
    }

    public void drawDealer(DealerDrawDto dealerDrawDto) {
        System.out.printf(DRAW_DEALER + "%n", dealerDrawDto.dealerName(), dealerDrawDto.boundary());
    }

    public void showResultStatistics(List<StatisticsDto> statisticsDtos, String dealerName, int dealerProfit) {
        System.out.println(PRINT_RESULT_PHRASE);
        System.out.printf(DEALER_RECORD_FORMAT + "%n", dealerName, dealerProfit);

        for (StatisticsDto statisticsDto : statisticsDtos) {
            System.out.printf(STATISTICS_FORMAT + "%n", statisticsDto.name(), statisticsDto.profit());
        }
    }

    public void congratulateBlackjack(String name) {
        System.out.printf(CONGRATULATE_BLACKJACK + "%n", name);
    }
}