package meesage;

public enum InputMessage {
    ASK_USER_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    ASK_ADD_CARD("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
    USER_INPUT_YES("y"),
    USER_INPUT_NO("n"),
    ASK_PLAYER_BETTING_AMOUNT("%s의 배팅 금액은?"),
    ;

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String format(Object... args) {return String.format(message, args);}

    public String getMessage() {
        return message;
    }
}
