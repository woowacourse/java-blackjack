package blackjack;

import java.util.List;

public class Dealer {
    private final CardHolder cardHolder;

    public Dealer(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public List<Integer> getPossibleSums() {
        return cardHolder.getPossibleSums();
    }
}
