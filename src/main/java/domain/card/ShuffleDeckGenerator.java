package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ShuffleDeckGenerator implements DeckGenerator{
    @Override
    public List<Card> generate() {
        List<Card> originDeck = addAllCards();
        Collections.shuffle(originDeck);
        return originDeck;
    }

    private List<Card> addAllCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(Arrays.asList(CloverCard.values()));
        cards.addAll(Arrays.asList(DiamondCard.values()));
        cards.addAll(Arrays.asList(HeartCard.values()));
        cards.addAll(Arrays.asList(SpadeCard.values()));

        return cards;
    }
}
