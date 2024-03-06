package domain.participant;

import domain.Name;
import domain.card.Card;
import domain.card.ParticipantCards;
import java.util.Collections;
import java.util.List;

public class Participant {

    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final ParticipantCards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new ParticipantCards();
    }

    public void receive(Card receivedCard) {
        cards.receive(receivedCard);
    }

    public void receive(List<Card> receivedCards) {
        cards.receive(receivedCards);
    }

    public int score() {
        return cards.calculateScore();
    }

    public boolean isBusted() {
        return cards.calculateScore() > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
