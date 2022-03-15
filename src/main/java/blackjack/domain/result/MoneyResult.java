package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Money;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoneyResult {

    private final Map<Participant, Money> moneys;

    public MoneyResult() {
        this.moneys = new LinkedHashMap<>();
    }

    public void calculateParticipantMoney(final Dealer dealer, final Participants participants) {
        for (Participant participant : participants) {
            final Money money = participant.getMoney();
            moneys.put(participant, money.calculate(Result.of(dealer, participant)));
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
