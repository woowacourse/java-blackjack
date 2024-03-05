package domain;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final HoldingCards holdingCards;

    public Player(String name) {
        this.name = name;
        this.holdingCards = new HoldingCards(new ArrayList<>());
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        validateCanDraw();
        holdingCards.add(deck.draw(cardDrawStrategy));
    }

    private void validateCanDraw() {
        if (getSummationCardPoint().isBiggerThan(new SummationCardPoint(21))) {
            throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
        }
    }

    public SummationCardPoint getSummationCardPoint() {
        return holdingCards.calculateTotalPoint();
    }
}
