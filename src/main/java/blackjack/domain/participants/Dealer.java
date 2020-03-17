package blackjack.domain.participants;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;

    private Hand hand;
    private Map<Result, Integer> result;

    public Dealer() {
        this.hand = new Hand();
        this.result = new HashMap<>();
    }

    public int countAddedCard() {
        return hand.countAddedCard();
    }

    @Override
    public void set(final Result result) {
        this.result.put(result, this.result.getOrDefault(result, 0) + 1);
    }

    @Override
    public void draw(Card card) {
        hand.add(card);
    }

    @Override
    public int score() {
        return hand.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck.pop());
        }
    }

    public boolean needsMoreCard() {
        return hand.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isBusted() {
        return hand.isBusted();
    }

    @Override
    public String handStatus() {
        return hand.toString();
    }

    public int getResult(final Result result) {
        return this.result.getOrDefault(result, 0);
    }

    @Override
    public String getName() {
        return "딜러";
    }
}
