public class PlayerName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    private final String value;

    public PlayerName(String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름의 길이는 1 ~ 20 글자 사이로 입력해주세요.");
        }
    }

    public String getValue() {
        return value;
    }
}
