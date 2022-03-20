package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Cards;
import java.util.Objects;
import java.util.Set;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";

    private final String name;
    private final Cards cards;

    public Participant(String name) {
        this(name, new Cards());
    }

    public Participant(String name, Cards cards) {
        Objects.requireNonNull(name, "이름의 값이 null 입니다.");
        Objects.requireNonNull(cards, "카드들의 값이 null 입니다.");
        validateName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    public abstract boolean isAvailableHit();

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.addScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public Cards getCardsInfo() {
        return cards;
    }

    public Set<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Participant)) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
