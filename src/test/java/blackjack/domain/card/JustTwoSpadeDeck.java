package blackjack.domain.card;

public class JustTwoSpadeDeck implements Deck {

    @Override
    public Card pick() {
        return Card.of(CardNumber.TWO, Type.SPADE);
    }
}
