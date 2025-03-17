package game;

import strategy.setting.DeckSettingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final Cards cards;

    public Deck(DeckSettingStrategy strategy) {
        this.cards = initialize(strategy);
    }

    public Cards initialize(DeckSettingStrategy strategy) {
        return strategy.initialize();
    }

    public Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(this.cards.drawCard());
        cards.add(this.cards.drawCard());
        return new Cards(cards);
    }

    public Card drawOneCard() {
        return cards.drawCard();
    }

    public int getSize() {
        return cards.getSize();
    }
}
