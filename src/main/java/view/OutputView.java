package view;

import util.Parser;

import java.util.List;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String SHOW_CARD = "%s카드: %s";
    private static final String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String SHOW_RESULT = SHOW_CARD + " - 결과: %d";
    private static final String PRINT_RESULT_PHRASE = "## 최종 승패";
    private static final String DEALER_RECORD_FORMAT = "딜러: %d승 %d패";
    private static final String STATISTICS_FORMAT = "%s: %s";

    public void drawCard(String dealerName, List<String> playerNames) {
        String namesResult = String.join(", ", playerNames);
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n", namesResult);
    }

    public void showCard(String name, List<String> cards) {
        System.out.println(SHOW_CARD.formatted(name,String.join(", ", cards)));
    }

    public void showCardsAndScore(String name, List<String> cards, Integer totalScore) {
        System.out.println(SHOW_RESULT.formatted(name,String.join(", ",cards),totalScore));
    }

    public void drawDealer() {
        System.out.println(DRAW_DEALER);
    }


    public void showResultStatistics(String name, String result) {
        System.out.println(STATISTICS_FORMAT.formatted(name, result));
    }
}
