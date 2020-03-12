package blackjack.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;
    public static final String SPACE = " ";

    private Hand hand;
    private Map<Result, Integer> result;

    public Dealer() {
        this.hand = new Hand();
        this.result = new HashMap<>();
    }

    public String getName() {
        return "딜러";
    }

    public int addedCardCount() {
        return hand.addedCardCount();
    }

    @Override
    public String gameResult() {
        return Arrays.stream(Result.values())
            .filter(result -> countResult(result) != 0)
            .map(result -> countResult(result) + result.getValue())
            .collect(Collectors.joining(SPACE));
    }

    @Override
    public void set(final Result result) {
        this.result.putIfAbsent(result, 0);
        this.result.put(result, this.result.get(result) + 1);
    }

    public boolean needsMoreCard() {
        return hand.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public void draw(Deck deck) {
        hand.add(deck.pop());
    }

    // 테스트용
    void draw(Card card) {
        hand.add(card);
    }

    @Override
    public int score() {
        return hand.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck);
        }
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isBusted() {
        return score() > 21;
    }

    @Override
    public String handStatus() {
        return hand.toString();
    }

    public int countResult(final Result result) {
        return this.result.getOrDefault(result, 0);
    }
}
