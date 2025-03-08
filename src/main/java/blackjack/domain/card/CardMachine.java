package blackjack.domain.card;

import blackjack.domain.GameRule;

public class CardMachine {

    private final Cards cards;

    private CardMachine(Cards cards) {
        this.cards = cards;
    }

    public static CardMachine initialize() {
        Cards cards = Cards.empty();

        for (int i = 0; i < GameRule.INITIAL_CARD_DECK_COUNT.getValue(); i++) {
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
