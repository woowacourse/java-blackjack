package domain;

import static domain.BlackJackGame.BLACKJACK_SCORE;

import domain.constants.CardCommand;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canPickCard(CardCommand cardCommand) {
        return CardCommand.HIT.equals(cardCommand) && isNotBusted();
    }

    public boolean isNotSameScoreAs(final Player other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final int otherScore) {
        return calculateResultScore() > otherScore;
    }

    public boolean hasMoreScoreThan(final Player other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public int calculateResultScore() {
        return hand.calculateResultScore();
    }

    public boolean hasLessOrSameCardThan(final Player other) {
        return getTotalSize() <= other.getTotalSize();
    }

    public boolean cannotDraw() {
        return hand.calculateResultScore() > BLACKJACK_SCORE;
    }

    public int getTotalSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
