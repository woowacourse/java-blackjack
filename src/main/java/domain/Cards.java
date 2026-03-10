package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Cards {
    private final List<Card> cards;

    public Cards(final Random random) {
        cards = new ArrayList<>();
        initializeCards();
        Collections.shuffle(cards, random);
    }

    private void initializeCards() {
        for (Suit suit : Suit.values()) {
            addSuitCards(suit);
        }
    }

    private void addSuitCards(Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    public boolean canDrawCard(){
        return !cards.isEmpty();
    }

    public Card draw(){
        if (!canDrawCard()) {
            throw new IllegalStateException("카드가 모두 소진되었습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
