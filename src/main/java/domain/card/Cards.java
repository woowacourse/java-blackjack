package domain.card;

import java.util.List;

public class Cards {
    List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card){
        cards.add(card);
    }

    public boolean hasAce() {
        for (Card card : cards) {
            return card.isAce();
        }

        return false;
    }

    public int total() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public int size(){
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }
}
