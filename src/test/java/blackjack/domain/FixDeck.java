package blackjack.domain;

public class FixDeck implements Deck {
    @Override
    public Card pick() {
        return new Card(CardNumber.TEN, Type.SPADE);
    }
}
