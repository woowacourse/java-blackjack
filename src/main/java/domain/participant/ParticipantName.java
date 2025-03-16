package domain.participant;

public record ParticipantName(String name) {

    public ParticipantName {
        validateBlank(name);
    }

    private void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 빈값을 입력 할 수 없습니다.");
        }
    }
}
