package blackjack.domain.participant;

public class ParticipantName {

    private static final String NAME_EMPTY_ERROR = "[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.";

    private final String value;

    public ParticipantName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String getValue() {
        return value;
    }
}
