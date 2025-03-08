package blackjack.domain.card;

public class CardDeck {

    private final Cards cards;

    private CardDeck(Cards cards) {
        this.cards = cards;
    }

    public static CardDeck initialize() {
        Cards cards = Cards.empty();

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(CardFactory.create(suit, rank));
            }
        }

        return new CardDeck(cards);
    }

    public Cards getCards() {
        return cards;
    }
}
