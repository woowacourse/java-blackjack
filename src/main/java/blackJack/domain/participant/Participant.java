package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    public static final int BLACK_JACK_CARD_COUNT = 2;
    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";
    private static final String ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";
    private static final int BLACK_JACK = 21;
    private static final int OTHER_SCORE_OF_ACE_DENOMINATION = 11;
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

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && calculateFinalScore() == BLACK_JACK;
    }

    public int calculateFinalScore() {
        final int score = calculateScore();
        if (hasAce() && score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore() <= BLACK_JACK) {
            return score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore();
        }
        return score;
    }

    // TODO
    // Cards 일급컬렉션을 만들어보자
    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
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
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
