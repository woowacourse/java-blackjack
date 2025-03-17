package blackjack.card;

public class CardMachine {

    private static final int MIN_CARD_DECK_COUNT = 6;

    private final Cards cards;

    private CardMachine(Cards cards) {
        this.cards = cards;
    }

    public static CardMachine initialize(int playerCount) {
        Cards cards = Cards.empty();

        for (int i = 0; i < Math.max(MIN_CARD_DECK_COUNT, playerCount); i++) {
            cards.add(CardDeck.initialize());
        }

        cards.shuffle();
        return new CardMachine(cards);
    }

    public Card drawCard() {
        return cards.draw();
    }

    protected Cards getCards() {
        return cards;
    }
}
