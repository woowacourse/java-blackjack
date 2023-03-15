package domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int DEALER_POSITION = 0;

    private Players players;
    private Dealer dealer;
    private Deck deck;

    public void dealFirstHands(final ShuffleStrategy shuffleStrategy) {
        Objects.requireNonNull(players, "플레이어가 없는 상태에서 카드를 나눠줄 수 없습니다.");

        deck = Deck.createFullDeck();
        deck.shuffle(shuffleStrategy);

        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        players.receiveCard(deck);
        players.receiveCard(deck);
    }

    public boolean hasAnyPlayerToDeal() {
        return players.hasAnyPlayerToDeal();
    }

    public Player getPlayerToDecide() {
        return players.getPlayerToDecide();
    }

    public void hitOrStand(final HitOrStand hitOrStand) {
        if (hitOrStand == HitOrStand.HIT) {
            players.dealToCurrentPlayer(deck.draw());
            return;
        }
        players.standCurrentPlayer();
    }

    public boolean shouldDealerDrawCard() {
        return dealer.shouldDrawCard();
    }

    public void dealCardToDealer() {
        dealer.receiveCard(deck.draw());
    }

    public int getDealerEarning() {
        return calculateTotalEarningOfPlayers() * -1;
    }

    private Integer calculateTotalEarningOfPlayers() {
        return getPlayersEarnings().values()
                                   .stream()
                                   .reduce(Integer::sum)
                                   .orElseThrow(IllegalStateException::new);
    }

    public Map<String, Integer> getPlayersEarnings() {
        return dealer.calculateEarnings(players);
    }

    public List<Participant> getParticipants() {
        final List<Participant> participants = players.getPlayers()
                                                      .stream()
                                                      .map(Participant.class::cast)
                                                      .collect(Collectors.toList());
        participants.add(DEALER_POSITION, dealer);
        return participants;
    }

    public void setParticipants(final Map<String, Integer> bettingAmounts) {
        players = Players.from(bettingAmounts);
        dealer = new Dealer();
    }
}
