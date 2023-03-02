package domain;

import java.util.List;

public class Participant {

    private final Name name;
    private final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                if (score >= 11) {
                    score += 1;
                    continue;
                }
            }
            score += card.getScore();
        }
        return score;
    }
}
