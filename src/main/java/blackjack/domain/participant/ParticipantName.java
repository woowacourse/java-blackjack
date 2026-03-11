package blackjack.domain.participant;

public record ParticipantName(
        String name
) {

    private static final int MAX_NAME_LENGTH = 5;

    public ParticipantName {
        String stripped = name.strip();
        validate(stripped);
        name = stripped;
    }

    private void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException(String.format("플레이어 이름은 %d자가 넘을 수 없습니다.", MAX_NAME_LENGTH));
        }
    }

}
