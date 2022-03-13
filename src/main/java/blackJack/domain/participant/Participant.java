package blackJack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";
    private static final String ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private static final int OTHER_SCORE_OF_ACE_DENOMINATION = 11;
    public static final int STANDARD_SCORE_OF_CHANGE_ACE = 11;

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    abstract boolean hasNextTurn();

    public void receiveCard(Card card) {
        validateReceiveDuplicatedCard(card);
        cards.add(card);
    }

    private void validateReceiveDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD);
        }
    }

    public int getScore() {
        int score = calculateScore();

        if (hasAce() && score <= STANDARD_SCORE_OF_CHANGE_ACE) {
            score += OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore();
        }
        return score;
    }

    private int calculateScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getDenomination() == Denomination.ACE);
    }

    public List<Card> getCards() {
        return cards;
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
        Participant that = (Participant)o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
