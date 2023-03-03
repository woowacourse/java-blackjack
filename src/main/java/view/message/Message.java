package view.message;

public enum Message {

    PARTICIPANT_NAME_INPUT_MESSAGE("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    DRAW_MESSAGE("%s에게 2장을 나누었습니다."),
    CARD_MESSAGE("%s: %s"),
    DRAW_CARD_CARD_MESSAGE("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)"),
    BUST_MESSAGE("카드의 합이 21을 초과했습니다."),
    BLACKJACK_MESSAGE("축하드립니다! 블랙잭입니다!"),
    DEALER_DRAW_MESSAGE("%s는 16이하라 한장의 카드를 더 받았습니다."),
    PARTICIPANT_CARD_RESULT("%s 카드: %s - 결과: %d");

    private final String message;

    Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
