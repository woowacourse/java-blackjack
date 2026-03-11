package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.playerName = new PlayerName(name);
    }

    public void addCard(Card card) {
        cards.add(card);
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
        return applyAceBonus(score, hasAce);
    }

    private int applyAceBonus(int score, boolean hasAce) {
        if (isSoftHand(score, hasAce)) {
            return score + 10;
        }
        return score;
    }

    private boolean isSoftHand(int score, boolean hasAce) {
        return hasAce && score + 10 <= 21;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return playerName.getName();
    }
}
