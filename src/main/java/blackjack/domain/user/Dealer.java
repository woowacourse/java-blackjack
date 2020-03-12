package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.result.ResultType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends User {
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;
    private final Map<ResultType, Integer> results;

    public Dealer() {
        super(name);
        this.results = new HashMap<>();
    }

    public void computeResult(List<ResultType> playerResultType) {
        for (ResultType k : ResultType.values()) {
            results.put(ResultType.reverse(k), ResultType.computeSum(k, playerResultType));
        }
    }

    public int getResultSum(ResultType resultType) {
        return results.getOrDefault(resultType, 0);
    }

    public Card getFirstCard() {
        return super.getCards().getCard(CardDeck.FIRST_INDEX);
    }

    @Override
    public boolean canReceiveMoreCard() {
        if (super.computeSum() < LOWER_BOUND) {
            return true;
        }
        return false;
    }
}
