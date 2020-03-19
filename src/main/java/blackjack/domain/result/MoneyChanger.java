package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.money.Money;

public class MoneyChanger {
    Map<Participant, Money> moneyChanger;

    public MoneyChanger(final Participants participants) {
        moneyChanger = new HashMap<>();
        for (Participant participant : participants) {
            moneyChanger.put(participant, Money.zero());
        }
    }

    public void receive(final Participant participant, final String value) {
        Money currentMoney = moneyChanger.get(participant);
        moneyChanger.put(participant, currentMoney.add(Money.create(value)));
    }

    public Money get(Participant participant) {
        return moneyChanger.get(participant);
    }

}
