package view.message;

public enum MessageFormatter {

    PARTICIPANT_NAME_INPUT_MESSAGE("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    PLAYER_BETTING_MESSAGE("%s의 배팅 금액은?"),
    DRAW_MESSAGE("%s에게 2장을 나누었습니다."),
    CARD_MESSAGE("%s: %s"),
    START_CARD_MESSAGE("%s 카드: %s"),
    DRAW_CARD_CARD_MESSAGE("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)"),
    DEALER_DRAW_MESSAGE("%s는 16이하라 한장의 카드를 더 받았습니다."),
    PARTICIPANT_CARD_RESULT("%s 카드: %s - 결과: %d"),
    FINAL_GAME_RESULT("## 최종 수익"),
    PARTICIPANT_GAME_RESULT("%s: %s"),
    EXCEPTION_MESSAGE("[ERROR] %s");

    private final String message;

    MessageFormatter(final String message) {
        this.message = message;
    }

    public String format(Object... messages) {
        return String.format(this.message, messages);
    }
}
