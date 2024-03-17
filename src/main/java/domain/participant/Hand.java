package domain.participant;

import domain.playingcard.PlayingCard;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Hand {
    private static final int BLACKJACK_CONDITION_VALUE = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    private final List<PlayingCard> playingCards;

    public Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public Score getTotalScore() {
        return Scores.getScore(countTotalScore());
    }

    private int countTotalScore() {
        int totalScore = playingCards.stream()
                .mapToInt(playingCard -> playingCard.playingCardValue().getValue())
                .sum();

        if (hasAce(playingCards) && notOverToBlackjackConditionValue(totalScore)) {
            return totalScore + ACE_ADDITIONAL_VALUE;
        }
        return totalScore;
    }

    private boolean hasAce(final List<PlayingCard> playingCards) {
        return playingCards.stream()
                .anyMatch(PlayingCard::isAce);
    }

    private boolean notOverToBlackjackConditionValue(final int totalScore) {
        return (totalScore + ACE_ADDITIONAL_VALUE) <= BLACKJACK_CONDITION_VALUE;
    }

    public boolean isBlackJack() {
        return this.playingCards.size() == 2
                && this.countTotalScore() == BLACKJACK_CONDITION_VALUE;
    }

    public boolean isBust() {
        return countTotalScore() > BLACKJACK_CONDITION_VALUE;
    }

    public boolean isTotalScoreLessOrEqual(final int target) {
        return countTotalScore() <= target;
    }

    public List<PlayingCard> getPlayingCards() {
        return unmodifiableList(playingCards);
    }
}
