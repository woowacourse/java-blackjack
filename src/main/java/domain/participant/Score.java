package domain.participant;

import domain.playingcard.PlayingCard;

import java.util.List;

public record Score(int value) {
    private static final int BLACKJACK_CONDITION_VALUE = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    public static Score of(final List<PlayingCard> playingCards) {
        int totalScore = playingCards.stream()
                .mapToInt(playingCard -> playingCard.playingCardValue().getValue())
                .sum();

        if (hasAce(playingCards) && notOverToBlackjackConditionValue(totalScore)) {
            return new Score(totalScore + ACE_ADDITIONAL_VALUE);
        }

        return new Score(totalScore);
    }

    private static boolean hasAce(final List<PlayingCard> playingCards) {
        return playingCards.stream()
                .anyMatch(PlayingCard::isAce);
    }

    private static boolean notOverToBlackjackConditionValue(final int totalScore) {
        return (totalScore + ACE_ADDITIONAL_VALUE) <= BLACKJACK_CONDITION_VALUE;
    }

    public boolean isBigger(final Score other) {
        return this.value > other.value;
    }

    public boolean isBustValue() {
        return this.value > BLACKJACK_CONDITION_VALUE;
    }

    public boolean isEqualOrLess(int target) {
        return this.value <= target;
    }
}
