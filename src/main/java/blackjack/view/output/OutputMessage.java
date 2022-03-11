package blackjack.view.output;

public enum OutputMessage {

    MESSAGE_OF_REQUEST_PLAYER_NAMES("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    MESSAGE_FORMAT_OF_DISTRIBUTE_TWO_CARDS("딜러와 %s에게 2장의 카드를 나누었습니다."),
    MESSAGE_FORMAT_OF_FINAL_SCORE("%s: %s - 결과: %d"),
    MESSAGE_FORMAT_OF_REQUEST_DRAWING_CARD_CHOICE("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
    MESSAGE_OF_DEALER_DREW_CARD("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    MESSAGE_OF_MATCH_RESULT_TITLE("## 최종 승패"),
    EMPTY_STRING("");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
