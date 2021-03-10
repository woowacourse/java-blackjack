package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Bust extends Finished{
    public Bust(Cards cards) {
        super(cards);
    }
}
