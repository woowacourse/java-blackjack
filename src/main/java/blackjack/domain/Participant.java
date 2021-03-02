package blackjack.domain;

public class Participant {

    private static final String INVALID_PARTICIPANT_NAME = "플레이어 이름은 양쪽 공백을 제외한 1글자 이상이어야 합니다.";
    private static final int INVALID_NAME_LENGTH = 0;

    private final String name;

    public Participant(String name) {
        validateName(name);
        this.name = name.trim();
    }

    private void validateName(String name) {
        int trimNameLength = name.trim()
                                 .length();
        if (trimNameLength == INVALID_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_PARTICIPANT_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
