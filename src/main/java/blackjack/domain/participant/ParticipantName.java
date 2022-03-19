package blackjack.domain.participant;

public class ParticipantName {

    public static final String NAME_EMPTY_ERROR = "플레이어 이름에 빈 값이 올 수 없습니다.";

    private final String value;

    public ParticipantName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String getValue() {
        return value;
    }
}
