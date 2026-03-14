package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.money.Money;
import blackjack.model.result.PlayerResult;
import java.math.BigDecimal;
import java.util.List;

public class Player extends Participant {

    private Money bettingMoney;

    private Player(String name, Hands hands) {
        super(name, hands);
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    @Override
    public List<CardDto> getInitCards() {
        return hands.getAllCards();
    }

    public void bet(double amount) {
        this.bettingMoney = Money.of(amount);
    }

    public Money calculateProfit(PlayerResult result) {
        if (result == PlayerResult.WIN) {
            return calculateBlackjackBonus();
        }
        if (result == PlayerResult.LOSE) {
            return bettingMoney.multiply(-1);
        }
        return Money.zero();
    }

    private Money calculateBlackjackBonus() {
        if (checkBonus()) {
            return bettingMoney.multiply(1.5);
        }
        return bettingMoney;
    }
}
