package domain;

import java.util.*;

public class BlackJackGame {

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer;

    public BlackJackGame(PlayerNames playerNames, Dealer dealer) {
        this.dealer = dealer;

        List<String> names = playerNames.names();
        for (String name : names) {
            Name Playername = new Name(name);
            Player player = new Player(Playername, dealer.dealHand());
            this.players.add(player);
        }
    }

    public void hitPlayer(Player targetPlayer) {
        if (targetPlayer.isHittable()) {
            targetPlayer.hit(dealer.dealCard());
        }
    }

    public boolean hitDealer() {
        if (dealer.isHittable()) {
            hitPlayer(dealer);
            return true;
        }
        return false;
    }

    public Map<Player, Result> getGameResults() {
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, Result.of(dealer, player));
        }
        return results;
    }

    public List<Player> getEveryParticipants() {
        List<Player> participants = new LinkedList<>(players);
        participants.add(0, dealer);
        return participants;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
