package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Participant {

    private static final String NAME_EMPTY_ERROR = "[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.";
    private static final int BUST_THRESHOLD_NUMBER = 21;

    private final String name;
    protected ParticipantCards cards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public void initCards(List<Card> cards) {
        this.cards = new ParticipantCards(new ArrayList<>(cards));
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBurst() {
        return getScore() > BUST_THRESHOLD_NUMBER;
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
