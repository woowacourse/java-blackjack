package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardBundle {
    private final List<Card> cards;

    public CardBundle() {
        this.cards = new ArrayList<>();
        initCards();
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getShuffledAllCards() {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return Collections.unmodifiableList(shuffledCards);
    }

    private void initCards() {
        Arrays.stream(Shape.values()).forEachOrdered(this::pickRank);
    }

    private void pickRank(Shape shape) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(shape, rank));
        }
    }
}
