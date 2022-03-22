package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Score;
import java.util.Objects;

public abstract class Participant {
    private static final int FIRST_CARD_INDEX = 0;
    private static final int SECOND_CARD_INDEX = 1;
    private static final String NAME_ERROR = "[Error] 이름은 빈 값일 수 없습니다.";

    protected final String name;
    protected List<Card> myCards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(NAME_ERROR);
        }
    }

    abstract public boolean canDraw();

    public void addCard(Card card) {
        myCards.add(card);
    }

    public Score score() {
        return Score.from(myCards);
    }

    public boolean isHit() {
        return Score.from(myCards).isHit();
    }

    public boolean isBust() {
        return Score.from(myCards).isBust();
    }

    public boolean isBlackjack() {
        List<Card> initTwoCards = myCards.subList(FIRST_CARD_INDEX, SECOND_CARD_INDEX + 1);
        return Score.from(initTwoCards).isMax();
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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<Card> getMyCards() {
        return Collections.unmodifiableList(myCards);
    }

    public String getName() {
        return name;
    }
}
