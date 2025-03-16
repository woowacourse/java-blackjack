package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.betting.Bet;
import model.betting.BetOwnable;
import model.betting.Bets;
import model.deck.Card;
import model.deck.Deck;
import model.deck.Gameable;
import model.result.GameResult;

public final class Dealer implements BetOwnable, Gameable {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private ParticipantHand participantHand; //TODO not final
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

    public void dealInitialCards(final Deck deck, final Players players) {
        //TODO : INITIAL_DEAL_COUNT 가 여기 있어야 할까?
        players.getPlayers().forEach(player ->
                player.dealInitialCards(deck)
        );
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
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
    public int calculateRevenue() {
        return bets.calculateDealerRevenue();
    }

    //TODO 꼭 dealer에게 있어야 할까?
    public void receiveBet(Bet bet) {
        this.bets.add(bet);
    }

    public void updateBetOwnerWhenPlayerLose(Player player, GameResult gameResult) {
        if (gameResult == GameResult.LOSE) {
            bets.updateOwner(player, this);
        }
    }

    public void updateBetAmountWhenPlayerBlackJack(Player player, GameResult gameResult) {
        if (gameResult == GameResult.WIN && player.isBlackjack()) { //TODO 상태 -> 객체 분리 시도하기
            bets.updateBetAmount(player);
        }
    }

    public Bet findBetByPlayer(Player player) {
        return bets.findByBetter(player);
    }
}

