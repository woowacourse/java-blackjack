package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.dto.TotalScoreDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int ADDITIONAL_SCORE_FOR_ACE = 10;
    protected static final int GOAL_SCORE = 21;

    protected final String name;
    protected int score = 0;
    protected final BettingMoney bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
        this.bettingMoney = new BettingMoney();
    }

    public abstract boolean isHittable();

    public abstract void betMoney(int money);

    public void addCard(Card card) {
        cards.add(card);
        score += card.getValue();
    }

    public TotalScoreDto computeTotalScore() {
        this.computeAce();
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

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }

    private void computeAce() {
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
