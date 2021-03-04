package blackjack.domain;

import java.util.List;

public class Dealer extends Playable {
    private static final int MAX_AVAILABLE_TO_GET_CARD = 16;
    public Dealer(List<Card> cards) {
        super("딜러",cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= MAX_AVAILABLE_TO_GET_CARD;
    }
}
