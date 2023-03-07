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

    public Result competeWith(Participant player) { // TODO: 2023/03/07 Score로 분리
        if (player.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        return competeByScore(player.calculateBlackjackScore());
    }

    private Result competeByScore(int playerScore) {
        int dealerScore = this.calculateBlackjackScore();

        if (playerScore > dealerScore) {
            return Result.LOSE;
        }
        if (playerScore < dealerScore) {
            return Result.WIN;
        }

        return Result.DRAW;
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
