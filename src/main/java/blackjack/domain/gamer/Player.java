package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    private final Bet bet;

    public Player(String name, int amount) {
        super(name);
        this.bet = new Bet(amount);
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
