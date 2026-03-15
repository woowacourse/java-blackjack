package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.money.Money;
import blackjack.model.result.PlayerResult;
import java.util.List;

public class Player extends Participant {

    private static final double NATURAL_BLACKJACK_BONUS = 1.5;

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

    public Money getPlayerProfit(Dealer dealer) {
        PlayerResult result = getPlayerResult(dealer);
        return calculatePlayerProfit(result);
    }

    private PlayerResult getPlayerResult(Dealer dealer) {
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

    private Money calculatePlayerProfit(PlayerResult result) {
        if (result == PlayerResult.WIN) {
            return checkNaturalBlackjack();
        }
        if (result == PlayerResult.LOSE) {
            return bettingMoney.multiply(-1);
        }
        return Money.zero();
    }

    private Money checkNaturalBlackjack() {
        if (isBlackjack() && hands.isTwoCardHand()) {
            return bettingMoney.multiply(NATURAL_BLACKJACK_BONUS);
        }
        return bettingMoney;
    }
}
