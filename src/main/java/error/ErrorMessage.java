package error;

public enum ErrorMessage {
    IS_BLANK("공백이 입력되었습니다."),
    INPUT_ONLY_Y_OR_N("입력은 y 혹은 n으로만 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}
