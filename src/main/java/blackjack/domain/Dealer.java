package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Dealer {
    public static final int DEALER_DRAW_CRITERIA = 17;

    private String name;
    private Hand hand;
    private Map<Result, Integer> result;

    public Dealer() {
        this.name = "딜러";
        this.hand = new Hand();
        this.result = new HashMap<>();
    }

    public void draw(Deck deck) {
        hand.add(deck.pop());
    }

    public String getName() {
        return name;
    }

    public boolean needsMoreCard() {
        return hand.calculate() < DEALER_DRAW_CRITERIA;
    }

    // 테스트용
    void draw(Card card) {
        hand.add(card);
    }
}
