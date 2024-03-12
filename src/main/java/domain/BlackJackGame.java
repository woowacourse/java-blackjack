package domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        dealer.hit(dealer.dealCard());
    }

    public boolean isDealerHittable() {
        return dealer.isHittable();
    }

    public Map<Player, Result> getGameResults() {
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, Result.of(dealer, player));
        }
        return results;
    }

    public List<Participant> getEveryParticipants() {
        List<Participant> participants = new LinkedList<>(players);
        participants.add(0, dealer);
        return participants;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
