package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.money.Money;
import blackjack.model.result.PlayerResult;
import java.util.List;

public class Player extends Participant {

    private static final double NATURAL_BLACKJACK_BONUS = 1.5;

    private Money bettingMoney;

    private Player(final String name, final Hands hands, final Money bettingMoney) {
        super(name, hands);
        this.bettingMoney = bettingMoney;
    }

    public static Player of(final String name) {
        return new Player(name, Hands.empty(), Money.zero());
    }

    @Override
    public List<CardDto> getInitCards() {
        return hands.getAllCards();
    }

    public void bet(final double amount) {
        this.bettingMoney = Money.of(amount);
    }

    public Money getPlayerProfit(final Dealer dealer) {
        PlayerResult result = getPlayerResult(dealer);
        return calculatePlayerProfit(result);
    }

    private PlayerResult getPlayerResult(final Dealer dealer) {
        if (this.isBust()) {
            return PlayerResult.LOSE;
        }

        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }

        if (dealer.hasHigherScoreThan(this)) {
            return PlayerResult.LOSE;
        }

        if (this.hasHigherScoreThan(dealer)) {
            return PlayerResult.WIN;
        }

        return PlayerResult.DRAW;
    }

    private Money calculatePlayerProfit(final PlayerResult result) {
        if (result == PlayerResult.WIN) {
            return checkBlackJack();
        }
        if (result == PlayerResult.LOSE) {
            return bettingMoney.multiply(-1);
        }
        return Money.zero();
    }

    private Money checkBlackJack() {
        if (isBlackjack()) {
            return bettingMoney.multiply(NATURAL_BLACKJACK_BONUS);
        }
        return bettingMoney;
    }
}
