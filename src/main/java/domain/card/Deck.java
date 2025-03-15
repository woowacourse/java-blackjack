package domain.card;


import java.util.ArrayList;
import java.util.List;

public class Deck {
    
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card drawCard() {
        validateEmpty();
        return cards.removeLast();
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 남은 카드가 없습니다.");
        }
    }
}
