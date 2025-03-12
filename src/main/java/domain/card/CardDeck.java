package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards = new ArrayList<>();

    public CardDeck() {
        initializeCardPack();
    }

    private void initializeCardPack() {
        Arrays.stream(Rank.values())
                .forEach(rank -> Arrays.stream(Shape.values())
                        .forEach(shape -> cards.add(new Card(rank, shape))));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        if (cards.isEmpty()) {
            initializeCardPack();
        }
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
