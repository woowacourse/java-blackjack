package blackjack.domain.user;

import blackjack.domain.card.Cards;

public class Player extends User {

    private final Bet bet;

    public Player(String name, Bet bet, Cards cards) {
        super(name, cards);
        this.bet = bet;
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public boolean isHit() {
        return !cards.isBust();
    }

}
