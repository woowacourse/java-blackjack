package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_STANDARD = 21;

    protected final List<Card> cards;
    protected final String name;

    protected Participant(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    abstract protected boolean isDealer();

    abstract protected boolean isUser();

    public String getName() {
        return this.name;
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int cardSum() {
        return ScoreCalculator.cardSum(cards);
    }

    public boolean isBust() {
        return cardSum() > BUST_STANDARD;
    }

    public boolean isHit() {
        return cardSum() < BUST_STANDARD;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && ScoreCalculator.cardSum(cards) == BUST_STANDARD;
    }

    public boolean isMoreScore(Participant other) {
        return cardSum() > other.cardSum();
    }

    public boolean isSameScore(Participant other) {
        return cardSum() == other.cardSum();
    }
}
