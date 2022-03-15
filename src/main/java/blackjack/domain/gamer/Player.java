package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    private final Bet money;

    public Player(String name, int money) {
        super(name);
        this.money = new Bet(money);
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
