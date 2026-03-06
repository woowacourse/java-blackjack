package view;

import dto.DealerDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import util.Parser;

import java.util.List;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String SHOW_CARD = "%s카드: %s";
    private static final String DRAW_DEALER = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String SHOW_RESULT = SHOW_CARD + " - 결과: %d";
    private static final String PRINT_RESULT_PHRASE = "## 최종 승패";
    private static final String DEALER_RECORD_FORMAT = "%s: %d승 %d패";
    private static final String STATISTICS_FORMAT = "%s: %s";

    //
    public void drawCard(NamesDto namesDto) {
        String namesResult = String.join(", ", namesDto.playerNames());
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n",namesDto.dealerName(), namesResult);
    }

    //
    public void showCard(PlayerCardsDto playerCardsDto) {
        System.out.println(SHOW_CARD.formatted(playerCardsDto.name(),String.join(", ", playerCardsDto.cards())));
    }

    public void showCardsAndScore(PlayerCardsDto playerCardsDto, Integer totalScore) {
        System.out.println(SHOW_RESULT.formatted(playerCardsDto.name() ,String.join(", ", playerCardsDto.cards())
                ,totalScore));
    }


    public void drawDealer(DealerDto dealerDto) {
        System.out.printf(DRAW_DEALER + "%n", dealerDto.dealerName(), dealerDto.boundary());
    }


    public void showResultStatistics(String name, String result) {
        System.out.println(STATISTICS_FORMAT.formatted(name, result));
    }
}
