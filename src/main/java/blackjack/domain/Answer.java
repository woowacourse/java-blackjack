package blackjack.domain;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static boolean isYes(String value) {
        return YES.answer.equals(value);
    }
}
