package blackjack.view.dto;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoundStatusDto2 {
    private Dealer dealer;
    private List<Player> players;

    public RoundStatusDto2(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Map<String, List<Card>> getStatus() {
        Map<String, List<Card>> status = new LinkedHashMap<>();
        status.put(dealer.getName(), dealer.getCards());
        players.forEach(player -> status.put(player.getName(), player.getCards()));
        return status;
    }
}
