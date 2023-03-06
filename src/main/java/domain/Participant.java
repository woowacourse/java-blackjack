package domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private static final int BUST_LIMIT = 21;

    protected final PlayerName playerName;
    protected final Cards cards = new Cards();

    public Participant(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int calculateBlackjackScore() {
        int sumOfScore = cards.getSumOfScore();

        return applyAce(sumOfScore, cards.countAce());
    }

    private int applyAce(int score, int aceCount) {
        while (score > BUST_LIMIT && aceCount > 0) {
            score -= TrumpCardNumber.getAceGap();
            aceCount--;
        }

        return score;
    }

    public boolean isBusted() {
        return calculateBlackjackScore() > BUST_LIMIT;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return playerName.getName();
    }

    public abstract List<Card> getInitialCards();
}
