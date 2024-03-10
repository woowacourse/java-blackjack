package domain.participant;

import domain.playingcard.PlayingCard;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Hand {
    public static final int BLACKJACK_CONDITION = 21;

    private final List<PlayingCard> playingCards;

    public Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public Score getTotalScore() {
        int result = 0;
        for (PlayingCard playingCard : playingCards) {
            result = playingCard.addValue(result);
        }

        return new Score(result);
    }

    public boolean isBust() {
        return getTotalScore().isBigger(BLACKJACK_CONDITION);
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public List<PlayingCard> getPlayingCards() {
        return unmodifiableList(playingCards);
    }

    public boolean isBlackJack() {
        PlayingCard firstCard = playingCards.get(0);
        PlayingCard secondCard = playingCards.get(1);

        return (firstCard.isAce() && secondCard.isTenValueCard())
                || (firstCard.isTenValueCard() || secondCard.isAce());
    }

    public boolean isLowerToBlackjackConditionValue() {
        return getTotalScore().isLower(BLACKJACK_CONDITION);
    }
}
