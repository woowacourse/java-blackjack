package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedSequenceDeck implements Deck {

    private final List<Card> cards;

    public FixedSequenceDeck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck generateDeck(Card... cards) {
        if (cards.length < 2) {
            throw new IllegalArgumentException("최소 카드는 두 장 필요");
        }
        return new FixedSequenceDeck(new ArrayList<>(Arrays.asList(cards)));
    }

    @Override
    public Cards initialDraw() {
        return new Cards(draw(), draw());
    }

    @Override
    public Card draw() {
        return cards.remove(0);
    }

    @Override
    public int size() {
        return cards.size();
    }
}
