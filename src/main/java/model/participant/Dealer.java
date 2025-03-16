package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.betting.Bet;
import model.hand.HardHand;
import model.hand.ParticipantHand;
import model.participant.role.BetOwnable;
import model.betting.Bets;
import model.deck.Card;
import model.deck.Deck;
import model.participant.role.Bettable;
import model.participant.role.GameProcessable;
import model.participant.role.Gameable;

public final class Dealer implements BetOwnable, Gameable, GameProcessable {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

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

    @Override
    public void splitInitialDeck(Deck deck, Gameable gamer) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> gamer.receiveCard(deck.pick())
        );
    }

    @Override
    public void receiveBet(Bet bet) {
        this.bets.add(bet);
    }

    @Override
    public void updateBetOwnerFrom(BetOwnable beforeOwner) {
        bets.updateOwner(beforeOwner, this);
    }

    @Override
    public void updateBetAmountOf(Bettable better) {
        bets.updateBetAmount(better);
    }

    @Override
    public Bet findBetByBetter(Bettable better) {
        return bets.findByBetter(better);
    }

    public int calculateRevenue() {
        return bets.calculateDealerRevenue();
    }
}

