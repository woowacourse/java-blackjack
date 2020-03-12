package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends User {
    private static final String CANT_FIND_CARD_MSG = "카드가 존재하지 않습니다.";
    public static final int LOWER_BOUND = 16;
    private final Map<Result, Integer> results;

    public Dealer() {
        super("딜러");
        this.results = new HashMap<>();
    }

    public void computeResult(List<Result> playerResult) {
        for (Result k : Result.values()) {
            results.put(Result.reverse(k), Result.getSum(k, playerResult));
        }
    }

    public Card getFirstCard() {
        return super.getCards().getCards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_FIND_CARD_MSG));
    }

    @Override
    public boolean canReceiveMoreCard() {
        if (super.computeSum() < LOWER_BOUND) {
            return true;
        }
        return false;
    }
}
