package blackjack.domain.gamer;

import blackjack.domain.Profit;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Player extends Gamer {

    private static final int OPEN_HAND_COUNT = 2;
    private final Profit profit;

    public Player(String name, int money,  Hands hands) {
        super(name, hands);
        this.profit = new Profit(money);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.cardsOf(OPEN_HAND_COUNT);
    }

    public int calculateProfit(ResultType resultType) {
        if (ResultType.WIN.equals(resultType)) {
            if (hands.isBlackjack()) {
                return profit.blackjackWin();
            }
            return profit.win();
        }
        if (ResultType.LOSE.equals(resultType)) {
            return profit.lose();
        }
        return 0;
    }
}
