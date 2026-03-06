package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card peek(){
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getTotalSum() {
        int aceNum = 0;
        int sum = 0;

        for (Card card : cards) {
            if (card.getRank().isAce()) {
                aceNum += 1;
                continue;
            }
            sum += card.getRank().getScoreValue();
        }

        for (int i = aceNum; i > 0; i--) {
            sum += Rank.decideAceValue(sum, i);
        }

        return sum;
    }

    public boolean decideBurst(int sum) {
        if (sum > 21) {
            return true;
        }
        return false;
    }

    public Card pull(){
        return cards.removeFirst();
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(card -> card.toString())
                .collect(Collectors.joining(", "));
    }
}
