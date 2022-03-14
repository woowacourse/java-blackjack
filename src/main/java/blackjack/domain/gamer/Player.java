package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    private final Money money;

    public Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    public BlackJackResult match(Dealer dealer) {
        return BlackJackResult.of(this.cards, dealer.cards);
    }

    public boolean isSameName(String name) {
        return this.getName()
                .isSame(name);
    }

    public boolean isBust() {
        return cards.isBust();
    }
}
