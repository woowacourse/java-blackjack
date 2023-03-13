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
    private BettingInfo playerBettingInfo;

    public void makeBet(final Map<String, Integer> bettingAmounts) {
        playerBettingInfo = new BettingInfo(bettingAmounts);
    }

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

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Map<String, Integer> getPlayersEarnings() {
        return playerBettingInfo.getEarnings(dealer.battleWith(players));
    }

    public List<Participant> getParticipants() {
        final List<Participant> participants = players.getPlayers()
                                                      .stream()
                                                      .map(player -> (Participant) player)
                                                      .collect(Collectors.toList());
        participants.add(DEALER_POSITION, dealer);
        return participants;
    }

    public void setParticipants(final List<String> playerNames) {
        players = Players.from(playerNames);
        dealer = new Dealer();
    }
}
