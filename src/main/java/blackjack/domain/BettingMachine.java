package blackjack.domain;

import blackjack.domain.player.Participant;
import blackjack.domain.result.Result;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingMachine {

    private final Map<Participant, Money> moneys;

    public BettingMachine() {
        this.moneys = new LinkedHashMap<>();
    }

    public void betMoney(final Participant participant, final Money money) {
        moneys.put(participant, money);
    }

    public void calculateMoney(final Map<Participant, Result> result) {
        for (Participant participant : result.keySet()) {
            final Money money = moneys.get(participant);
            moneys.put(participant, money.calculate(result.get(participant)));
        }
    }

    public Money calculateDealerMoney() {
        final Long money = moneys.values()
                .stream()
                .map(Money::getValue)
                .reduce(0L, Long::sum);
        return Money.from(-money);
    }

    public Map<Participant, Money> getMoneys() {
        return new LinkedHashMap<>(moneys);
    }
}
