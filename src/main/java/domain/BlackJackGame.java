package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer;

    public BlackJackGame(PlayerNames playerNames, CardDeck cardDeck) {

        this.dealer = new Dealer(cardDeck);

        List<String> names = playerNames.names();
        for (String s : names) {
            Name name = new Name(s);
            Player player = new Player(name, dealer.dealHand());
            this.players.add(player);
        }
    }

    public boolean hitPlayer(Player player) {
        Player targetPlayer = players.stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .get();
        return isHitSuccess(targetPlayer);
    }

    public boolean hitDealer() {
        return isHitSuccess(dealer);
    }

    private boolean isHitSuccess(Player targetPlayer) {
        if (targetPlayer.isHittable()) {
            targetPlayer.hit(dealer.dealCard());
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
        ArrayList<Player> participants = new ArrayList<>(players);
        participants.add(0, dealer);
        return participants;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
