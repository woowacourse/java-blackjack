package domain;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }
}
