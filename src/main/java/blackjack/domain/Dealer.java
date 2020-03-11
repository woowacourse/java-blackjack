package blackjack.domain;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer {
    public static final int LOWER_BOUND = 16;
    private Cards cards;
    private final Map<Result, Integer> results;

    public Dealer() {
        cards = new Cards();
        results = new HashMap<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSum() {
        return cards.getSum();
    }

    public boolean needMoreCard() {
        return getSum() <= LOWER_BOUND;
    }

    public void computeResult(List<Result> playerResult) {
        for (Result k : Result.values()) {
            results.put(Result.reverse(k), Result.getSum(k, playerResult));
        }
    }

    public int getResultSum(Result result) {
        return results.get(result);
    }
}
