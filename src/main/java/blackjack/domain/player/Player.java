package blackjack.domain.player;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;

public class Player extends Participant {

    private Result result;

    public Player(String name) {
        super(name);
    }

    public boolean canDraw() {
        return cards.getScore() < BLACKJACK_NUMBER;
    }

    public void matchCards(Cards dealerCards) {
        result = dealerCards.getOtherCardsCompareResult(cards);
    }

    public Result getResult() {
        return result;
    }
}
