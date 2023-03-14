package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;

public abstract class Participant {

    private static final String DEALER_NAME = "딜러";

    private final ParticipantName participantName;
    private final Hand hand;

    public Participant(ParticipantName participantName, Hand hand) {
        this.participantName = participantName;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand.hitCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean participantHasAceCard() {
        return hand.hasAceCard();
    }

    public boolean judgeBlackjack() {
        return hand.isBlackjack();
    }

    public int calculateCardNumber() {
        return hand.calculateCardNumber();
    }

    public ParticipantName getParticipantName() {
        return participantName;
    }

    public String getName() {
        return participantName.getName();
    }

    public CardNumber getCardNumber(int index) {
        return hand.getReceivedCards().get(index).getCardNumber();
    }

    public CardSuit getCardSuit(int index) {
        return hand.getReceivedCards().get(index).getCardSuit();
    }

    public boolean isDealer() {
        return participantName.equals(new ParticipantName(DEALER_NAME));
    }

    abstract boolean decideHit();
}
