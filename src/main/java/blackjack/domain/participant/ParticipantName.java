package blackjack.domain.participant;

public class ParticipantName {
    public static final String PARTICIPANT_NAME_NOT_VALID = "[ERROR] 플레이어 이름은 비어 있을 수 없습니다.";

    private final String name;

    public ParticipantName(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(PARTICIPANT_NAME_NOT_VALID);
        }
        this.name = value.trim();
    }

    public String getValue() {
        return name;
    }

}
