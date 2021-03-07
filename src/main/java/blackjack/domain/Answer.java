package blackjack.domain;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static boolean isNo(String answer) {
        return NO.answer.equals(answer);
    }

    public static boolean isYes(String answer) {
        return YES.answer.equals(answer);
    }
}
