package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int FRONT_CARD_INDEX = 0;

    private final List<Card> cards;

    public Deck() {
        this.cards = generateCardsForBlackJack();
    }

    private List<Card> generateCardsForBlackJack() {
        List<Card> cards = new ArrayList<>();
        gatherCards(cards);
        Collections.shuffle(cards);
        return cards;
    }

    private void gatherCards(List<Card> cards) {
        Arrays.stream(Rank.values()).forEach(rank -> {
            Arrays.stream(Suit.values()).map(suit -> new Card(rank, suit)).forEach(cards::add);
        });
    }

    public Card draw() {
        Card card = cards.get(FRONT_CARD_INDEX);
        cards.remove(FRONT_CARD_INDEX);
        return card;
    }
}
