package domain.view;

public class OutputWriter {
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final String INPUT_NAME_GUIDE_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String DEAL_INITIAL_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private final String ASK_DRAW_CARD_GUIDE_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final String DEALER_ADDITIONAL_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final String BETTING_GUIDE_MESSAGE = "%s의 배팅 금액은?";
    private final String FINAL_PROFIT_TITLE_MESSAGE = "## 최종 수익";
    private final String GAMER_PROFIT_FORMAT = "%s: %.0f";
    private final String PLAYER_HAND_MESSAGE = "%s카드: %s";
    private final String CARD_SUM_RESULT_MESSAGE = "%s카드: %s - 결과: %d";

    public void printInputNameGuideMessage() {
        System.out.print(INPUT_NAME_GUIDE_MESSAGE);
        printLineSeparator();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.print(errorMessage);
        printLineSeparator();
    }

    public void printBettingGuideMessage(String name) {
        System.out.printf(BETTING_GUIDE_MESSAGE, name);
        printLineSeparator();
    }

    public void printDealInitialCardMessage(String names) {
        printLineSeparator();
        System.out.printf(DEAL_INITIAL_CARD_MESSAGE, names);
        printLineSeparator();
        printLineSeparator();
    }

    public void printAllParticipantsHand(String playerName, String playerCards) {
        System.out.printf(PLAYER_HAND_MESSAGE, playerName, playerCards);
        printLineSeparator();
    }

    public void printDrawCardGuideMessage(String playerName) {
        System.out.printf(ASK_DRAW_CARD_GUIDE_MESSAGE, playerName);
        printLineSeparator();
    }

    public void printDealerAdditionalDrawCardMessage() {
        printLineSeparator();
        System.out.print(DEALER_ADDITIONAL_DRAW_MESSAGE);
        printLineSeparator();
        printLineSeparator();
    }

    public void printProfitTitleMessage() {
        printLineSeparator();
        System.out.print(FINAL_PROFIT_TITLE_MESSAGE);
        printLineSeparator();
    }

    public void printGamerProfit(String playerName, double profit) {
        System.out.printf(GAMER_PROFIT_FORMAT, playerName, profit);
        printLineSeparator();
    }

    public void printFinalResultMessage(String gamerName, String playerCards, int resultScore) {
        System.out.printf(CARD_SUM_RESULT_MESSAGE, gamerName, playerCards, resultScore);
        printLineSeparator();
    }

    private void printLineSeparator() {
        System.out.print(LINE_SEPARATOR);
    }
}
