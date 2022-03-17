package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Score;

public abstract class Participant {
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

    public void addCard(Card card) {
        myCards.add(card);
    }

    public Score score() {
        return Score.from(myCards);
    }

    public List<Card> getMyCards() {
        return Collections.unmodifiableList(myCards);
    }

    public String getName() {
        return name;
    }
}
