package domain.participant;

import controller.dto.HandStatus;
import domain.card.Card;
import domain.game.BlackJackGame;
import domain.game.DecisionToContinue;
import domain.game.Deck;
import java.util.List;

public abstract class Participant {
    private final String name;
    private final Hand hand;

    protected Participant(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> pickCard(final Deck deck, final int count) {
        for (int index = 0; index < count; index++) {
            hand.saveCard(deck.pick());
        }
        return hand.getCards();
    }

    public boolean isBusted() {
        return hand.calculateScore() > BlackJackGame.BLACKJACK_SCORE;
    }

    public boolean isNotBusted() {
        return !isBusted();
    }

    protected int calculateScore() {
        return hand.calculateScore();
    }

    private int calculateResultScore() {
        return hand.calculateResultScore();
    }

    public HandStatus createHandStatus() {
        return new HandStatus(name, hand);
    }

    public boolean isNotSameScoreAs(final Participant other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final Participant other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public boolean hasLessOrSameCardThan(final Participant other) {
        return getCardSize() <= other.getCardSize();
    }

    public int getCardSize() {
        return hand.size();
    }

    public String getName() {
        return this.name;
    }

    abstract public boolean canPickCard(final DecisionToContinue decision);
}
