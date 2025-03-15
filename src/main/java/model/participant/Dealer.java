package model.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import model.betting.Bet;
import model.deck.Card;
import model.deck.Deck;

public final class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final ParticipantHand participantHand;
    private final List<Bet> bets;

    public Dealer() {
        this.participantHand = new ParticipantHand();
        this.bets = new ArrayList<>();
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
        return bets.stream()
                .mapToInt(Bet::calculateDealerRevenue)
                .sum();
    }

    public void updateBetOwnerToDealerFrom(Player player) {
        Bet updatingBet = bets.stream()
                .filter(bet -> bet.getOwner().equals(player))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("플레이어의 배팅 금액이 저장되지 않았습니다."));
        this.bets.remove(updatingBet);
        this.bets.add(updatingBet.changeOwnerTo(this));
    }

    public Bet findBetByPlayer(Player player) {
        return bets.stream()
                .filter(bet -> bet.betterEquals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당하는 플레이어를 찾을 수 없습니다."));
    }

    public void updateBetAmountWhenBlackJack(Player player) {
        Bet bet = findBetByPlayer(player);
        this.bets.remove(bet);
        this.bets.add(bet.increase(1.5));
    }

    public List<Bet> getBets() {
        return Collections.unmodifiableList(bets);
    }
}

