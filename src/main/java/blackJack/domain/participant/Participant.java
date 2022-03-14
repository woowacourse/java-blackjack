package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Cards;
import blackJack.domain.card.Denomination;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";
    private static final int BLACK_JACK = 21;
    private static final int OTHER_SCORE_OF_ACE_DENOMINATION = 11;

    private final String name;
    private final Cards cards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    abstract boolean hasNextTurn();

    public void receiveCard(Card card) {
        cards.receiveCard(card);
    }

    public boolean isBlackJack() {
        return cards.isBlackJackPossibleCount() && calculateFinalScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateFinalScore() > BLACK_JACK;
    }

    public int calculateFinalScore() {
        final int score = cards.calculateScore();
        if (cards.hasAce() && score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore() <= BLACK_JACK) {
            return score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore();
        }
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Participant))
            return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
