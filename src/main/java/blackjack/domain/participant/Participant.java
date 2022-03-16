package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public abstract class Participant {

    private static final String UNINITIALIZED_CARDS_ERROR_MESSAGE = "초기 카드를 받지 않았습니다.";

    private final ParticipantName name;
    protected ParticipantCards cards;

    public Participant(String name) {
        this.name = new ParticipantName(name);
    }

    public void initCards(List<Card> cards) {
        this.cards = new ParticipantCards(new ArrayList<>(cards));
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        validateInitialCards();
        return Collections.unmodifiableList(cards.getCards());
    }

    private void validateInitialCards() {
        if (cards == null) {
            throw new NullPointerException(UNINITIALIZED_CARDS_ERROR_MESSAGE);
        }
    }
}
