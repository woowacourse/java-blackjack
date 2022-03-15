package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.dto.TotalScoreDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {

    private static final int ADDITIONAL_SCORE_FOR_ACE = 10;
    protected static final int GOAL_SCORE = 21;

    protected final String name;
    private final List<Card> cards = new ArrayList<>();
    private int score = 0;

    public Participant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
        score += card.getValue();
    }

    public TotalScoreDto computeTotalScore() {
        this.endTurn();
        return new TotalScoreDto(this);
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

    private void endTurn() {
        if (isContainAce() && isBetterToGiveMoreForAce()) {
            score += ADDITIONAL_SCORE_FOR_ACE;
        }
    }

    private boolean isContainAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean isBetterToGiveMoreForAce() {
        return score + ADDITIONAL_SCORE_FOR_ACE <= GOAL_SCORE;
    }
}
