package blackjack.model.participant;

import blackjack.common.error.ErrorCode;
import blackjack.model.Hands;
import blackjack.model.card.CardDto;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;

    protected final String name;
    protected final Hands hands;

    public Participant(String name, Hands hands) {
        validateName(name);
        validateHands(hands);

        this.name = name;
        this.hands = hands;
    }

    public abstract void pickInitCards(CardDeck cardDeck);

    public void pickAdditionalCard(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }

    public boolean isBust() {
        return hands.hasScoreHigherThan(BLACKJACK_SCORE);
    }

    public boolean hasHigherScoreThan(Participant other) {
        return this.hands.hasScoreHigherThan(other.getCurrentTotalScore());
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }

    public List<CardDto> getAllCard() {
        return hands.getAllCard();
    }

    public List<CardDto> getOpenedCards() {
        return hands.getOpenedCards();
    }

    public String getName() {
        return this.name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.NO_NAME_PARTICIPANT_NAME.getMessage());
        }
    }

    private void validateHands(Hands hands) {
        if (hands == null) {
            throw new IllegalArgumentException(ErrorCode.NULL_HANDS.getMessage());
        }
    }
}
