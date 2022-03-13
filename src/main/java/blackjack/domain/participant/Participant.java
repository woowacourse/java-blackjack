package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Participant {

    private static final int BUST_THRESHOLD_NUMBER = 21;

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
        return getScore() > BUST_THRESHOLD_NUMBER;
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
