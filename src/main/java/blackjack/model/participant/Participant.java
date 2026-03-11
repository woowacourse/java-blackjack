package blackjack.model.participant;

import blackjack.common.error.ErrorCode;
import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    protected final Hands hands;

    public Participant(String name, Hands hands) {
        validateName(name);
        validateHands(hands);

        this.name = name;
        this.hands = hands;
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

    public abstract List<CardDto> getInitCards();

    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    public void pickAdditionalCard(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }

    public boolean isBust() {
        return hands.hasScoreHigherThan(BLACKJACK_SCORE);
    }

    public boolean hasHigherScoreThan(Participant other) {
        return this.hands.hasScoreHigherThan(other.getFinalScore());
    }

    public int getFinalScore() {
        return hands.calculateTotalScore();
    }

    public List<CardDto> getAllCards() {
        return hands.getAllCards();
    }

    public String getName() {
        return this.name;
    }
}
