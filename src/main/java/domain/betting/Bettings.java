package domain.betting;

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
}
