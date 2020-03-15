package domain;

public enum Answer {
    Y("y"),
    N("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public boolean isYes() {
        return this == Y;
    }
}
