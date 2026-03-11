package domain.card;

import static domain.Constant.DELIMITER;

import domain.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card peek() {
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }

    public int getTotalSum() {
        int aceNum = getAceAmount();
        int sum = getAmountWithoutAce();

        for (int i = aceNum; i > 0; i--) {
            sum += Rank.decideAceValue(sum, i);
        }
        return sum;
    }

    private int getAceAmount() {
        int aceAmount = 0;
        for (Card card : cards) {
            aceAmount += card.getOneIfAce();
        }
        return aceAmount;
    }

    private int getAmountWithoutAce() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getRankValueIfNotAce();
        }
        return sum;
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(card -> card.toString())
                .collect(Collectors.joining(DELIMITER));
    }
}
