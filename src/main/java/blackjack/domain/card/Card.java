package blackjack.domain.card;

public record Card(CardNumber number, CardShape shape) {

    public int getValue() {
        return number.getValue();
    }

    public boolean isAce() {
        return number.equals(CardNumber.ACE);
    }
}
