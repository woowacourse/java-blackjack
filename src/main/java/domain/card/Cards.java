package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Card draw(){
        return cards.removeFirst();
    }

    public Card peek() {
        return cards.getFirst();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public int countAce() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
