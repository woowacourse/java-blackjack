package domain.view.message;

public enum Message {

    PARTICIPANT_NAME_INPUT_MESSAGE("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    DRAW_MESSAGE("%s에게 2장을 나누었습니다."),
    CARD_MESSAGE("%s: %s");

    private final String message;

    Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
