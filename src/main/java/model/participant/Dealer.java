package model.participant;

import java.util.List;
import model.hand.HardHand;
import model.hand.ParticipantHand;
import model.participant.role.BetOwnable;
import model.betting.Bets;
import model.deck.Card;
import model.participant.role.Gameable;

public final class Dealer implements BetOwnable, Gameable {
    private static final int DEALER_HIT_THRESHOLD = 16;

    private ParticipantHand participantHand;
    private final Bets bets;

    public Dealer() {
        this.participantHand = new HardHand();
        this.bets = new Bets();
    }

    @Override
    public void receiveCard(final Card card) {
        if (card.isSoftCard()) {
            this.participantHand = this.participantHand.cloneToSoftHand();
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
    public boolean canHit() {
        return participantHand.checkScoreBelow(DEALER_HIT_THRESHOLD);
    }

    @Override
    public List<Card> openInitialDeal() {
        return List.of(participantHand.getCards().getFirst());
    }

    @Override
    public List<Card> getHandCards() {
        return participantHand.getCards();
    }
}
