package view;

public class OutputWriter {

    private final String LINE_SEPARATOR = System.lineSeparator();
    private final String INPUT_NAME_GUIDE_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String DEAL_INITIAL_CARD_MESSAGE = "딜러와 %s에게 %d장을 나누었습니다.";
    private final String ASK_DRAW_CARD_GUIDE_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final String DEALER_ADDITIONAL_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final String FINAL_RESULT_TITLE_MESSAGE = "## 최종 승패";
    private final String FINAL_RESULT_PLAYER = "%s: %s";
    private final String FINAL_RESULT_DEALER = "딜러: %s";
    private final String PLAYER_HAND_MESSAGE = "%s카드: %s";
    private final String CARD_SUM_RESULT_MESSAGE = "%s카드: %s - 결과: %d";
    private final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printInputNameGuideMessage() {
        System.out.println(INPUT_NAME_GUIDE_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }

    public void printDealInitialCardMessage(String names, int initialCardCount) {
        System.out.print(LINE_SEPARATOR);
        System.out.printf(DEAL_INITIAL_CARD_MESSAGE, names, initialCardCount);
        System.out.print(LINE_SEPARATOR);
        System.out.print(LINE_SEPARATOR);
    }

    public void printAllParticipantsHand(String playerName, String playerCards) {
        System.out.printf(PLAYER_HAND_MESSAGE, playerName, playerCards);
        System.out.print(LINE_SEPARATOR);
    }

    public void printDrawCardGuideMessage(String playerName) {
        System.out.printf(ASK_DRAW_CARD_GUIDE_MESSAGE, playerName);
        System.out.print(LINE_SEPARATOR);
    }

    public void printDealerAdditionalDrawCardMessage() {
        System.out.print(LINE_SEPARATOR);
        System.out.println(DEALER_ADDITIONAL_DRAW_MESSAGE);
        System.out.print(LINE_SEPARATOR);
    }

    public void printFinalResultTitleMessage() {
        System.out.print(LINE_SEPARATOR);
        System.out.printf(FINAL_RESULT_TITLE_MESSAGE);
    }

    public void printFinalResultOfPlayer(String playerName, String status) {
        System.out.printf(FINAL_RESULT_PLAYER, playerName, status);
        System.out.print(LINE_SEPARATOR);
    }

    public void printFinalResultOfDealer(String status) {
        System.out.print(LINE_SEPARATOR);
        System.out.printf(FINAL_RESULT_DEALER, status);
        System.out.print(LINE_SEPARATOR);
    }

    public void printFinalResultMessage(String gamerName, String playerCards, int resultScore) {
        System.out.printf(CARD_SUM_RESULT_MESSAGE, gamerName, playerCards, resultScore);
        System.out.print(LINE_SEPARATOR);
    }

}
