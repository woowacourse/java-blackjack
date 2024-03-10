package domain.playingcard;

import static domain.participant.Hand.BLACKJACK_CONDITION;
import static domain.playingcard.PlayingCardValue.SMALL_ACE;

public class PlayingCard {
    private final PlayingCardShape playingCardShape;
    private final PlayingCardValue playingCardValue;

    public PlayingCard(final PlayingCardShape playingCardShape, final PlayingCardValue playingCardValue) {
        this.playingCardShape = playingCardShape;
        this.playingCardValue = playingCardValue;
    }

    public int addValue(final int inputValue) {
        if (playingCardValue.isAce()) {
            return inputValue + chooseAceValue(inputValue);
        }
        return inputValue + playingCardValue.getValue();
    }

    private int chooseAceValue(final int inputValue) {
        if (inputValue + playingCardValue.getValue() > BLACKJACK_CONDITION) {
            return SMALL_ACE.getValue();
        }
        return playingCardValue.getValue();
    }

    public PlayingCardShape getPlayingCardShape() {
        return playingCardShape;
    }

    public PlayingCardValue getPlayingCardValue() {
        return playingCardValue;
    }

    public boolean isAce() {
        return playingCardValue.isAce();
    }

    public boolean isTenValueCard() {
        return playingCardValue.isTenValueCard();
    }
}
