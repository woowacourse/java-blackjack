package domain;

public record ParticipantName(String name) {
    private static final String BLANK_NAME = "닉네임은 공백일 수 없습니다";

    public ParticipantName {
        validateName(name);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME);
        }
    }

    public boolean isMatch(ParticipantName name) {
        return this.equals(name);
    }
}
