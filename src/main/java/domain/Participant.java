package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private static final int BUST_LIMIT = 21;

    protected final PlayerName playerName;
    protected final Cards cards = Cards.getDefault();

    public Participant(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int calculateBlackjackScore() {
        return Score.from(cards).getValue();
    }

    public boolean isBusted() {
        return calculateBlackjackScore() > BUST_LIMIT;
    }

    public Result competeWith(Participant otherPlayer) {
        if (otherPlayer.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        Score score = Score.from(cards);
        Score otherScore = Score.from(otherPlayer.cards);
        return score.competeByScore(otherScore);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return playerName.getName();
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
}
