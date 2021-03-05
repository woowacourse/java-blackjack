package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private static final String INVALID_PARTICIPANT_NAME = "플레이어 이름은 양쪽 공백을 제외한 1글자 이상이어야 합니다.";
    private static final int INVALID_NAME_LENGTH = 0;

    private final String name;
    private final Cards cards;

    protected Participant(String name) {
        validateName(name);
        this.name = name.trim();
        this.cards = new Cards();
    }

    private void validateName(String name) {
        int trimNameLength = Objects.requireNonNull(name)
                .trim()
                .length();
        if (trimNameLength == INVALID_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_PARTICIPANT_NAME);
        }
    }

    public abstract boolean isAbleToReceiveCard();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void receiveCards(Cards receivedCards) {
        cards.addAll(receivedCards);
    }

    public int calculateFinalScore() {
        return cards.calculateFinalScore();
    }

    public int calculateScoreWhenAceIsMinimum() {
        return cards.calculateScoreWhenAceIsMinimum();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
