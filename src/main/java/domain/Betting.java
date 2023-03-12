package domain;

import java.util.Map;

public class Betting {

    private final Map<Name, Amount> betting;

    public Betting(Map<Name, Amount> betting) {
        this.betting = betting;
    }

    public int getAmountFromPlayerName(Name name) {
        return betting.get(name).getValue();
    }

}
