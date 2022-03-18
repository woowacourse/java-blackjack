package blackjack.domain.gamer;

import blackjack.domain.Answer;
import blackjack.domain.card.Card;
import blackjack.domain.result.BlackJackResult;

import java.util.List;
import java.util.function.UnaryOperator;

public class Player extends Gamer {
    private final BettingMoney bettingMoney;

    public Player(String name, List<Card> cards, int value) {
        super(name);
        for (Card card : cards) {
            addCard(card);
        }
        bettingMoney = new BettingMoney(value);
    }

    public BlackJackResult match(Dealer dealer) {
        return BlackJackResult.of(this, dealer);
    }

    public boolean isDrawPossible(UnaryOperator<String> operator) {
        return canDraw() && Answer.from(operator.apply(getName())).isYes();
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }

    @Override
    public boolean canDraw() {
        return getCardsNumberSum() <= MAX_CARD_VALUE;
    }
}
