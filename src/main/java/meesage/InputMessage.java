package meesage;

public enum InputMessage {
    ASK_USER_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
