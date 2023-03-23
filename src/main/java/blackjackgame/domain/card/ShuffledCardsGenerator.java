package blackjackgame.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public List<Card> generate(int deckCount) {
        List<Card> cards = new ArrayList<>();

        for (int count = 0; count < deckCount; count++) {
            addDeck(cards);
        }

        Collections.shuffle(cards);
        return cards;
    }

    private static void addDeck(List<Card> cards) {
        cards.addAll(Arrays.asList(CloverCard.values()));
        cards.addAll(Arrays.asList(DiamondCard.values()));
        cards.addAll(Arrays.asList(HeartCard.values()));
        cards.addAll(Arrays.asList(SpadeCard.values()));
    }
}
