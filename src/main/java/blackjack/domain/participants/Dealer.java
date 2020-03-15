package blackjack.domain.participants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;
    public static final String SPACE = " ";

    private Cards cards;
    private Map<Result, Integer> result;

    public Dealer() {
        this.cards = new Cards();
        this.result = new HashMap<>();
    }

    public int addedCardCount() {
        return cards.addedCardCount();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public String gameResult() {
        return Arrays.stream(Result.values())
            .filter(result -> countResult(result) != 0)
            .map(result -> countResult(result) + result.getValue())
            .collect(Collectors.joining(SPACE));
    }

    public int countResult(final Result result) {
        return this.result.getOrDefault(result, 0);
    }

    @Override
    public void set(final Result result) {
        this.result.putIfAbsent(result, 0);
        this.result.put(result, this.result.get(result) + 1);
    }

    @Override
    public void draw(Deck deck) {
        cards.add(deck.pop());
    }

    @Override
    public int score() {
        return cards.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck);
        }
    }

    private boolean needsMoreCard() {
        return cards.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String handStatus() {
        return cards.toString();
    }

    // 테스트용
    public void draw(Card card) {
        cards.add(card);
    }
}
