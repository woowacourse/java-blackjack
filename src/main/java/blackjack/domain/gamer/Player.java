package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    private final Bet bet;

    public Player(String name, int amount) {
        super(name);
        this.bet = new Bet(amount);
    }

    public int match(Dealer dealer) {
        BlackJackResult result = BlackJackResult.of(this.cards, dealer.cards);
        return bet.makeEarning(result);
    }

    public boolean isSameName(String name) {
        return this.getName().equals(name);
    }

    public boolean isBust() {
        return cards.isBust();
    }
}
