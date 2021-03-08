package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

import java.util.List;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public Player(List<Card> cards, String name) {
        super(cards, name);
    }

    public Result produceResult(User dealer) {
        return Result.decide(this, dealer);
    }
}
