package domain.card;

public record TrumpCard(Suit suit, CardValue cardValue) {

    public int cardNumberValue() {
        return cardValue.getValue();
    }

    public boolean isAce() {
        return cardValue.isAce();
    }

    public String getRank() {
        return cardValue.getRank();
    }

    public String getSuit() {
        return suit.suit();
    }
}
