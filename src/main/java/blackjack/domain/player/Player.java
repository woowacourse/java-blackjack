package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public Result matchCards(Cards dealerCards) {
        return dealerCards.compare(cards);
    }

}
