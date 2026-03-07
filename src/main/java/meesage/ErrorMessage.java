package meesage;

public enum ErrorMessage {

    EMPTY_INPUT("[ERROR] 사용자 이름은 공백을 입력할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
