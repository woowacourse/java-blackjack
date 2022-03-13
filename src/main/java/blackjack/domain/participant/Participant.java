package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.dto.CurrentCardsDto;
import blackjack.dto.TotalScoreDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {

    private static final int ADDITIONAL_SCORE_FOR_ACE = 10;
    protected static final int GOAL_SCORE = 21;

    private final String name;
    private final List<Card> cards = new ArrayList<>();
    private int score = 0;

    public Participant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
        score += card.getValue();
    }

    public CurrentCardsDto generateCurrentCardsDTO() {
        return new CurrentCardsDto(name, Collections.unmodifiableList(cards));
    }

    public TotalScoreDto computeTotalScore() {
        this.endTurn();
        return new TotalScoreDto(name, Collections.unmodifiableList(cards), score);
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
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        if (aceCount > 0 && score + ADDITIONAL_SCORE_FOR_ACE <= GOAL_SCORE) {
            score += ADDITIONAL_SCORE_FOR_ACE;
        }
    }
}
