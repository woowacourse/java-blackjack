package blackjack.domain.card;

public class JustTenSpadeDeck implements Deck {
    @Override
    public Card pick() {
        return Card.of(CardNumber.TEN, Type.SPADE);
    }
}
