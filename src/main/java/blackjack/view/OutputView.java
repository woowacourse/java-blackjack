package blackjack.view;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private OutputView() {
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printPlayerNameInstruction() {
        System.out.println(PLAYER_NAME_MESSAGE);
    }
}
