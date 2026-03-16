package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final ParticipantName name;
    protected final Hand hand;

    protected Participant(ParticipantName name) {
        this.hand = Hand.empty();
        this.name = name;
    }

    public Hand drawCards(CardDeck cardDeck, int count) {
        List<Card> cards = cardDeck.draw(count);
        return hand.addUp(cards);
    }

    public boolean hasHigherScoreThan(Participant other) {
        return this.hand.getResultScore() > other.hand.getResultScore();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public int getResultScore() {
        return hand.getResultScore();
    }

    public String toDisplayMyName() {
        return name.name();
    }

    public ParticipantName getName() {
        return name;
    }

    public List<String> disPlayMyCardBundle() {
        return hand.toDisplay();
    }

}
