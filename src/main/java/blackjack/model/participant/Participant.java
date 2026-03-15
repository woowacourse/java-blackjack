package blackjack.model.participant;

import blackjack.common.error.ErrorCode;
import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.carddeck.CardDeck;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    protected final Hands hands;

    public Participant(final String name, final Hands hands) {
        validateName(name);
        validateHands(hands);

        this.name = name;
        this.hands = hands;
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.NO_NAME_PARTICIPANT_NAME.getMessage());
        }
        if (!name.equals(name.strip())) {
            throw new IllegalArgumentException(ErrorCode.NAME_STARTS_OR_ENDS_WITH_SPACE.getMessage());
        }
    }

    private void validateHands(final Hands hands) {
        if (hands == null) {
            throw new IllegalArgumentException(ErrorCode.NULL_HANDS.getMessage());
        }
    }

    public abstract List<CardDto> getInitCards();

    public void pickInitCards(final CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    public void pickAdditionalCard(final CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }

    public boolean isBust() {
        return hands.hasScoreHigherThan(BLACKJACK_SCORE);
    }

    public boolean isBlackjack() {
        return hands.calculateTotalScore() == BLACKJACK_SCORE;
    }

    public boolean hasHigherScoreThan(final Participant other) {
        return this.hands.hasScoreHigherThan(other.getScore());
    }

    public int getScore() {
        return hands.calculateTotalScore();
    }

    public List<CardDto> getAllCards() {
        return hands.getAllCards();
    }

    public String getName() {
        return this.name;
    }
}
