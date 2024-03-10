package domain;

public abstract class Participant {
    private final String name;
    private final Hand hand;

    protected Participant(final String name, final Hand hand) {
        Validator.validateName(name);
        this.name = name;
        this.hand = new Hand();
    }

    private static class Validator {
        private static void validateName(final String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("이름으로 빈 문자열이 입력되었습니다.");
            }
        }
    }
}
