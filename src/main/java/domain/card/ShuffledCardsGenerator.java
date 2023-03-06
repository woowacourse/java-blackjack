package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();

        cards.addAll(Arrays.asList(CloverCard.values()));
        cards.addAll(Arrays.asList(DiamondCard.values()));
        cards.addAll(Arrays.asList(HeartCard.values()));
        cards.addAll(Arrays.asList(SpadeCard.values()));

        Collections.shuffle(cards);
        return cards;
    }
}
