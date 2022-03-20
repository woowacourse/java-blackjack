package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Cards;
import blackJack.domain.card.Score;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";

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

    abstract boolean canHit();

    public void hit(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack() {
        return getScore().isBlackJack() && cards.getCards().size() == 2;
    }

    public boolean isBurst() {
        return getScore().isBurst();
    }

    public List<Card> getCards() {
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
