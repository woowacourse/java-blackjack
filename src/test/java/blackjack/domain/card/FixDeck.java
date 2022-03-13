package blackjack.domain.card;

public class FixDeck implements Deck {
    @Override
    public Card pick() {
        return Card.of(CardNumber.TEN, Type.SPADE);
    }
}
