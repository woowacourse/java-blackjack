package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bettings {

    private final List<Betting> bettings;

    public Bettings(List<Betting> bettings) {
        this.bettings = bettings;
    }

    public List<Betting> getBettings() {
        return new ArrayList<>(bettings);
    }
}
