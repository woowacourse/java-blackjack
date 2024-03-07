package blackjack.view;

public class OutputView {
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";
    private static final String ASK_DRAW_MESSAGE = "는/은 한장의 카드를 더 받겠습니까?(예는 "
            + ACCEPT_DRAW_MESSAGE + ", 아니오는 " + REJECT_DRAW_MESSAGE + ")";

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여 할사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printAskDrawMessage(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
    }
}
