package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    private final PlayerName playerName;
    private final List<Card> cards = new ArrayList<>();

    protected Participant(String name) {
        this.playerName = new PlayerName(name);
    }

    public abstract boolean canHit();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return playerName.getName();
    }

    public int calculateScore() {
        int score = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            score += card.getScore();
            if (card.isAce()) {
                hasAce = true;
            }
        }
        if (hasAce && score + 10 <= 21) {
            return score + 10;
        }
        return score;
    }
}
