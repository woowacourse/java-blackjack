package blackjack.domain;

import blackjack.domain.player.Participant;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingMachine {

    private final Map<Participant, Money> moneys;

    public BettingMachine() {
        this.moneys = new LinkedHashMap<>();
    }

    public void betMoney(Participant participant, Money money) {
        moneys.put(participant, money.geOpposite());
    }

    public Map<Participant, Money> getMoneys() {
        return new LinkedHashMap<>(moneys);
    }
}
