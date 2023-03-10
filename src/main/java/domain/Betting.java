package domain;

import java.util.Map;

public class Betting {

    private final Map<Name, Money> betting;

    public Betting(Map<Name, Money> betting) {
        this.betting = betting;
    }

    public int getMoneyFromPlayerName(Name name) {
        return betting.get(name).getValue();
    }

}
