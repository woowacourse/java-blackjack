package domain;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {

    private final List<Card> cards;

    public CardGroup() {
        cards = new ArrayList<>();
    }

    public CardGroup(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreWithOutAce() {
        return cards.stream()
                .mapToInt(card -> card.getScore().cardScore)
                .sum();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int countCards() {
        return cards.size();
    }

    public int calculateAceScore() {
        return 0;
    }
}
