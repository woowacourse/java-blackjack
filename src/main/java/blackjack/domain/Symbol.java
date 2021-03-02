package blackjack.domain;

public enum Symbol {
    ACE(1),
    TWO(2);

    private final int value;

    Symbol(int value) {
        this.value = value;
    }
}
