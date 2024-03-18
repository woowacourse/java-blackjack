package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.*;

public class BlackJackGame {

    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.dealer = dealer;
        this.players = players.getPlayers();
    }

    public void initHand() {
        dealer.initHand(dealer.dealCard(2));

        for (Player player : players) {
            player.initHand(dealer.dealCard(2));
        }
    }

    public void hitParticipant(Participant participant) {
        participant.hit(dealer.dealCard());
    }

    public void hitDealer() {
        hitParticipant(dealer);
    }

    public boolean isDealerHittable() {
        return dealer.isHittable();
    }

    public Map<Participant, Integer> settleBets() {
        int dealerSettlement = 0;
        Map<Participant, Integer> settlement = new LinkedHashMap<>();

        for (Player player : players) {
            int playerSettlement = calculatePlayerSettlement(player);
            dealerSettlement -= playerSettlement;
            settlement.put(player, playerSettlement);
        }

        settlement.put(dealer, dealerSettlement);
        return settlement;
    }

    private int calculatePlayerSettlement(Player player) {
        int betAmount = player.getBetAmount();
        float winningRate = PlayerGameResult.of(dealer, player).getWinningRate();
        return (int) (betAmount * winningRate);
    }

    public List<Participant> getEveryParticipants() {
        List<Participant> participants = new LinkedList<>(players);
        participants.add(0, dealer);
        return Collections.unmodifiableList(participants);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
