package blackjack.domain;

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

    @Override
    public String getResult() {
        return parsedResult();
    }

    private String parsedResult() {
        return result.keySet().stream()
            .map(result -> this.result.get(result) + result.getValue())
            .collect(Collectors.joining(SPACE));
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
}
