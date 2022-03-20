package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;

public class Dealer {

    private final Participant participant;
    private final Deck deck;

    public Dealer(Participant participant, Deck deck) {
        this.participant = participant;
        this.deck = deck;
    }

    public static Dealer create() {
        return new Dealer(new Participant("딜러"), Deck.createShuffledCards());
    }

    public Card draw() {
        return deck.draw();
    }

    public boolean shouldReceive() {
        return participant.getCardTotalScore() <= 16;
    }

    public void hit(Card card) {
        if (shouldReceive()) {
            participant.hit(card);
        }
    }

    public boolean isReady() {
        return participant.isReady();
    }

    public Participant getParticipant() {
        return participant;
    }

    public State getState() {
        return participant.getState();
    }

    public Card getOpenCard() {
        return participant.getOpenCard();
    }
}
