package domain;

import java.util.ArrayList;
import java.util.List;

public class Bettings {

    private List<Betting> bettings;

    public Bettings(List<String> bettingsInput) {
        this.bettings = createBettings(bettingsInput);
    }

    private List<Betting> createBettings(List<String> bettingsInput) {
        List<Betting> bettings = new ArrayList<>();
        for (String input : bettingsInput) {
            bettings.add(new Betting(input));
        }

        return bettings;
    }

    public List<Betting> getBettings() {
        return new ArrayList<>(bettings);
    }
}
