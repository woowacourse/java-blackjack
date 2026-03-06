package view;

import java.util.List;

public class OutputView {
    private static final String DEAL_INITIAL_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String SHOW_CARD = "%s카드: ";
    private static final String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_RESULT_PHRASE = "## 최종 승패";
    private static final String DEALER_RECORD_FORMAT = "딜러: %d승 %d패";
    private static final String PLAYER_WIN_FORMAT = "%s: 승";
    private static final String PLAYER_LOSE_FORMAT = "%s: 패";

    public void drawCard(List<String> names) {
        String namesResult = String.join(", ", names);
        System.out.printf(DEAL_INITIAL_CARDS_MESSAGE + "%n", namesResult);
    }

    public void showCard(String name, List<String> cards) {
        System.out.println(SHOW_CARD.formatted(name));
    }

    public void drawDealer() {
        System.out.println(DRAW_DEALER);
    }


}
