package team.blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    private final Set<Card> cards = new HashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.stream().toList();
    }

    public int getCardCount() {
        return cards.size();
    }

    public int getScore() {

    }

}
