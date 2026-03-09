package blackjack.domain;

public record Card(
        CardValue cardValue,
        CardShape cardShape
) {

    public boolean isAce() {
        return cardValue.isAce();
    }

    public String getName() {
        return cardValue.getName() + cardShape.getName();
    }

    public int getCardValue() {
        return cardValue.getValue();
    }

}
