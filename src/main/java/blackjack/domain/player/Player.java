package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;

public class Player extends Participant {

    private Result result;

    public Player(String name) {
        super(name);
    }

    public boolean canDraw() {
        return cards.getScore() < 21;
    }

    public void matchCards(Cards otherCards) {
        if (isBlackjack()) {
            result = Result.LOSE;
            return;
        }
        result = otherCards.compare(cards);
    }

    public Result getResult() {
        return result;
    }
}
