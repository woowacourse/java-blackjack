package domain.card;

import static domain.card.PlayingCardValue.SMALL_ACE;
import static domain.card.constant.GameOption.BLACKJACK_CONDITION;

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
}
