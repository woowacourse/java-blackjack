package domain;

import java.util.List;

public abstract class Participant {

    private static final int MAXIMUM_SUM_OF_CARD = 21;
    private static final int INITIAL_CARD_SIZE = 2;

    protected final Cards cards;

    public boolean selectToPickOtherCard(final int cardBoxIndex) {
        return pickOtherCard(cardBoxIndex);
    }
    private boolean pickOtherCard(final int cardBoxIndex) {
        return this.cards.addCard(Deck.get(cardBoxIndex));
    }

    public PlayerState askPlayerState() {
        if (sumOfParticipantCards() > MAXIMUM_SUM_OF_CARD) {
            return PlayerState.MORE_THAN_MAXIMUM;
        }
        if (sumOfParticipantCards() == MAXIMUM_SUM_OF_CARD) {
            return PlayerState.EQUAL_WITH_MAXIMUM;
        }
        return PlayerState.LESS_THAN_MAXIMUM;
    }

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public int sumOfParticipantCards() {
        return cards.sumOfCards();
    }

    public boolean isBlackJack() {
        return sumOfParticipantCards() == MAXIMUM_SUM_OF_CARD && cards.sizeOfCards() == INITIAL_CARD_SIZE;
    }

    public List<String> copyCards() {
        return cards.copyCards();
    }
}
