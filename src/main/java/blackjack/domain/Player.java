package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Player extends Participant{
    private boolean stay = false;

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    private void addScore(Card card) {
        score += card.getValue();
    }

    public void addCard(Card card) {
        cards.add(card);
        addScore(card);

        if (score >= GOAL_SCORE) {
            stay = true;
        }
    }

    public void endTurn() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        boolean changed = true;
        while (aceCount-- > 0 && changed) {
            changed = moreScoreForAceOrNot();
        }
    }

    private boolean moreScoreForAceOrNot() {
        if (score + ADDITIONAL_SCORE_FOR_ACE <= GOAL_SCORE) {
            score += ADDITIONAL_SCORE_FOR_ACE;
            return true;
        }
        return false;
    }


    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean canHit() {
        return getScore() < GOAL_SCORE;
    }

    public boolean isWin(int score) {
        if (getScore() > GOAL_SCORE) {
            return false;
        }
        if (score > GOAL_SCORE) {
            return true;
        }
        return getScore() >= score;
    }

    public boolean isAbleToHit() {
        return !stay;
    }

    public void stay() {
        stay = true;
    }
}
