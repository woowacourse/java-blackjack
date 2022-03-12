package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    public Player(String name) {
        super(name);
    }

    public BlackJackResult match(Dealer dealer) {
        return BlackJackResult.of(this.cards, dealer.cards);
    }

    public boolean isSameName(String name) {
        return this.getName()
                .isSame(name);
    }
}
