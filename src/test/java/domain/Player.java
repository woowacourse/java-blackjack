package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<TrumpCard> trumpCards;
    private final DrawPolicy drawPolicy;
    private final int sum = 0;

    public Player(DrawPolicy drawPolicy) {
        this.drawPolicy = drawPolicy;
        trumpCards = new ArrayList<>();
    }

    public void addDraw(TrumpCard trumpCard) {
        trumpCards.add(trumpCard);
    }

    public boolean isBurst() {
        int sum = trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        return drawPolicy.isBurst(sum);
    }
}
