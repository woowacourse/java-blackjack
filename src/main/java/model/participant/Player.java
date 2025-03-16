package model.participant;

import java.util.List;
import model.betting.Bet;
import model.hand.HardHand;
import model.hand.ParticipantHand;
import model.participant.role.Bettable;
import model.deck.Card;
import model.participant.role.Gameable;

public final class Player implements Bettable, Gameable {
    private final String name;
    private ParticipantHand participantHand;

    public Player(final String name) {
        this.name = name;
        this.participantHand = new HardHand();
    }

    @Override
    public void receiveCard(final Card card) {
        if (card.isSoftCard()) {
            participantHand = participantHand.cloneToSoftHand();
        }
        participantHand.add(card);
    }

    @Override
    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    @Override
    public boolean isBlackjack() {
        return participantHand.checkBlackJack();
    }

    @Override
    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    @Override
    public List<Card> openInitialDeal() {
        return participantHand.getCards();
    }

    @Override
    public boolean canHit() {
        return !isBurst();
    }

    @Override
    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    @Override
    public Bet makeBet(int betAmount) {
        return new Bet(betAmount, this);
    }

    public String getName() {
        return name;
    }
}
