package domain.card;

import static domain.Constant.DELIMITER;

import domain.Rank;
import domain.Result;
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
        int aceCount = getAceAmount();
        int sum = getBaseSum();

        for (int i = 0; i < aceCount; i++) {
            if ((sum + Rank.ACE_ADDITIONAL_VALUE) <= Result.BLACKJACK_MAX_NUMBER) {
                sum += Rank.ACE_ADDITIONAL_VALUE;
            }
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

    private int getBaseSum() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(card -> card.toString())
                .collect(Collectors.joining(DELIMITER));
    }
}
