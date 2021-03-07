package blackjack.domain;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public boolean equals(String value) {
        return answer.equals(value);
    }
}
