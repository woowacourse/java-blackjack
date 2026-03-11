package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.card.CardDeck;
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
        return hand.addUp(Hand.from(cards));
    }

    public boolean hasCard(Card targetCard) {
        return hand.checkExist(targetCard);
    }

    public int getResultScore() {
        return hand.getResultScore();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public String toDisplayMyName() {
        return name.name();
    }

    public List<String> disPlayMyCardBundle() {
        return hand.toDisplay();
    }

}
