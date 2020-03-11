package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer {
    private static final String CANT_FIND_CARD_MSG = "카드가 존재하지 않습니다.";
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
        return cards.computeScore();
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

    public String getName() {
        return "딜러";
    }

    public Cards getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.getCards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_FIND_CARD_MSG));
    }
}
