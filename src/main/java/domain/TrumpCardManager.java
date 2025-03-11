package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class TrumpCardManager {
    private final Stack<TrumpCard> trumpCards = new Stack<>();

    public TrumpCardManager() {
        initCache();
    }

    public void initCache() {
        Arrays.stream(CardShape.values())
                .forEach(cardShape -> Arrays.stream(CardNumber.values())
                        .forEach(cardNumber -> trumpCards.push(new TrumpCard(cardShape, cardNumber))));
        Collections.shuffle(trumpCards);
    }

    public TrumpCard drawCard() {
        if (trumpCards.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return trumpCards.pop();
    }

}
