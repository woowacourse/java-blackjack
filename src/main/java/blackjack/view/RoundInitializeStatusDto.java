package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RoundInitializeStatusDto {
//    private Map<String, List<Card>> status = new HashMap<>();
    private Dealer dealer;
    private List<Player> players;

    public RoundInitializeStatusDto(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
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
