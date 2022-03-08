package blackjack;

public class Card {

    private final int number;

    public Card(int number) {
        validateCardNumberRange(number);
        this.number = number;
    }

    private void validateCardNumberRange(int number) {
        if (number < 1 || number > 13) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }
}
