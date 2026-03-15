package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.CardPoint;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this(new RandomShuffleStrategy());
    }

    public Deck(ShuffleStrategy shuffleStrategy) {
        this.deck = createDeck(shuffleStrategy);
    }

    private List<Card> createDeck(ShuffleStrategy shuffleStrategy) {
        List<Card> deck = new ArrayList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            for (CardPoint cardPoint : CardPoint.values()) {
                deck.add(new Card(cardPoint, cardPattern));
            }
        }
        shuffleStrategy.shuffle(deck);
        return deck;
    }

    public int getCount() {
        return deck.size();
    }

    public Card draw() {
        return deck.removeLast();
    }

}
