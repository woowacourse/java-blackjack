package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Player {

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isPossibleToPickCard() {
        return cards.calculateScore() <= 16;
    }
}
