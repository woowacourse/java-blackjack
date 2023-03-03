package blackjack.domain.participant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final String playersNames) {
        this.dealer = dealer;
        this.players = makePlayers(playersNames);
    }
    private List<Player> makePlayers(final String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(name -> new Player(new Name(name.strip())))
                .collect(Collectors.toList());
    }

    public Dealer getDealer(){
        return this.dealer;
    }
    public List<Player>  getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
