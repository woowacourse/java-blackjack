package domain;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import domain.strategy.DeckSettingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        cards.add(this.cards.drawOneCard());
        cards.add(this.cards.drawOneCard());
        return new Cards(cards);
    }

    public Card drawOneCard() {
        return cards.drawOneCard();
    }

    public int getSize() {
        return cards.getSize();
    }
}
