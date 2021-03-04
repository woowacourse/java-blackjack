package blackjack.domain;

import java.util.List;

public class Dealer extends Playable {
    public Dealer(List<Card> cards) {
        super("딜러",cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= 17;
    }
}
