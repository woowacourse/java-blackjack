package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.betting.Bet;
import model.betting.Bets;
import model.deck.Card;
import model.deck.Deck;

public final class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final ParticipantHand participantHand;
    private final Bets bets;

    public Dealer() {
        this.participantHand = new ParticipantHand();
        this.bets = new Bets();
    }

    public void receiveCard(final Card card) {
        participantHand.add(card);
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    public void dealInitialCards(final Deck deck, final Players players) {
        //TODO : INITIAL_DEAL_COUNT 가 여기 있어야 할까?
        players.getPlayers().forEach(player ->
                player.dealInitialCards(deck)
        );
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public boolean canHit() {
        return participantHand.checkScoreBelow(DEALER_HIT_THRESHOLD);
    }

    public List<Card> openInitialDeal() {
        return List.of(participantHand.getCards().getFirst());
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    public boolean isBlackJack() {
        return participantHand.checkBlackJack();
    }

    public void receiveBet(Bet bet) {
        this.bets.add(bet);
    }

    public int calculateRevenue() {
        return bets.calculateDealerRevenue();
    }

    public void updateBetOwnerToDealerFrom(Player player) {
        bets.updateOwner(player, this);
    }

    public Bet findBetByPlayer(Player player) {
        return bets.findByBetter(player);
    }

    public void updateBetAmountWhenBlackJack(Player player) {
        bets.updateBetAmount(player);
    }
}

