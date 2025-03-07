package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {

    private final List<Card> cards = new ArrayList<>();

    public CardPack() {
        initializeCardPack();
    }

    private void initializeCardPack() {
        for (Rank rank : Rank.values()) {
            for (Shape shape : Shape.values()) {
                Card card = new Card(rank, shape);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
