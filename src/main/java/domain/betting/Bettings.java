package domain.betting;

import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public record Bettings(List<Betting> bettings) {

    public Bettings() {
        this(List.of());
    }

    public Bettings(List<Betting> bettings) {
        this.bettings = List.copyOf(bettings);
    }

    public Bettings addBetting(Betting betting) {
        List<Betting> bettings = new ArrayList<>(this.bettings);
        bettings.add(betting);
        return new Bettings(bettings);
    }

    public Players getPlayers() {
        List<Player> players = bettings.stream()
                .map(Betting::player)
                .toList();
        return new Players(players);
    }
}
