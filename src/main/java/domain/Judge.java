package domain;

public class Judge {

    private final Deck deck;

    public Judge(Deck deck) {
        validate(deck);
        this.deck = deck;
    }

    private void validate(Deck deck) {
        validateNotNull(deck);
    }

    private void validateNotNull(Deck deck) {
        if (deck == null) {
            throw new IllegalStateException("심판은 덱을 가지고 있어야합니다.");
        }
    }

    public TrumpCard drawCard() {
        return deck.draw();
    }
}
