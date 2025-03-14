package blackjack.card;

public class CardDeck {

    private final Cards cards;

    private CardDeck(Cards cards) {
        this.cards = cards;
    }

    public static CardDeck initialize() {
        Cards cards = Cards.empty();

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(Card.of(suit, rank));
            }
        }

        return new CardDeck(cards);
    }

    protected Cards getCards() {
        return cards;
    }
}
