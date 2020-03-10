package domain.card;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;

    @Override
    public String toString() {
        return cardNumber.getScore() + cardSuit.getSuit();
    }
}
