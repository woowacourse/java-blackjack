package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final PlayingCards playingCards;

    private Deck(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck create() {
        List<Card> cards = new ArrayList<>();
        for(Type type : Type.values()) {
            cards.addAll(createBySymbol(type));
        }
        return new Deck(new PlayingCards(cards));
    }

    private static List<Card> createBySymbol(Type type) {
        List<Card> cards = new ArrayList<>();
        for(Symbol symbol : Symbol.values()) {
            cards.add(new Card(symbol,type));
        }
        return cards;
    }

    public Card handOutCard() {

        return null;
    }
}
