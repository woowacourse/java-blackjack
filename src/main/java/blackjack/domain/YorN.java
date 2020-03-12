package blackjack.domain;

public enum YorN {
    YES("y", true),
    NO("n", false);

    private String input;
    private boolean result;

    YorN(String input, boolean result) {
        this.input = input;
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
