package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final PlayerName playerName;
    protected final Cards cards = Cards.getDefault();

    public Participant(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public void receive(Cards cards) {
        for (Card card : cards.getCards()) {
            this.receive(card);
        }
    }

    public BlackjackScore calculateBlackjackScore() {
        return BlackjackScore.from(cards);
    }

    public boolean isBusted() {
        return calculateBlackjackScore().isGreaterThan(BlackjackScore.getMaxScore());
    }

    public Result competeWith(Participant otherPlayer) {
        if (otherPlayer.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        BlackjackScore blackjackScore = BlackjackScore.from(cards);
        BlackjackScore otherBlackjackScore = BlackjackScore.from(otherPlayer.cards);
        return blackjackScore.compete(otherBlackjackScore);
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean isAbleToReceiveCard();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return playerName.equals(that.playerName) && getCards().equals(that.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, getCards());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return playerName.getName();
    }
}
