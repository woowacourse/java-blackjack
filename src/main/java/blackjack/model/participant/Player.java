package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.money.Money;
import blackjack.model.result.PlayerResult;
import java.util.List;

public class Player extends Participant {

    private Money bettingMoney;

    private Player(final String name, final Hands hands, final Money bettingMoney) {
        super(name, hands);
        this.bettingMoney = bettingMoney;
    }

    public static Player of(final String name) {
        return new Player(name, Hands.empty(), Money.ZERO);
    }

    @Override
    public List<CardDto> getInitCards() {
        return hands.getAllCards();
    }

    public void bet(final double amount) {
        this.bettingMoney = Money.of(amount);
    }

    public Money getPlayerProfit(final Dealer dealer) {
        PlayerResult result = dealer.getPlayerResult(this);
        return bettingMoney.multiply(result.getProfitMultiple(isBlackjack()));
    }
}
