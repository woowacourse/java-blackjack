package domain.participant;

import java.util.List;

public record ParticipantName(String name) {
    private static final String BLANK_NAME = "닉네임은 공백일 수 없습니다";

    public static ParticipantName nameOf(String name) {
        validateName(name);
        return new ParticipantName(name);
    }

    public static List<ParticipantName> namesOf(List<String> names) {
        return names.stream()
                .map(ParticipantName::new)
                .toList();
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME);
        }
    }

    public boolean isMatch(ParticipantName name) {
        return this.equals(name);
    }
}
