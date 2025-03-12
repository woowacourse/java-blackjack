package blackjack.domain.player;

import blackjack.domain.BetAmount;
import blackjack.domain.card.Card;

import java.util.List;

public class Gambler extends Player {
    private final BetAmount betAmount;

    public Gambler(PlayerName name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public Gambler(String name) {
        super(name);
        betAmount = new BetAmount(0);
    }

    @Override
    public List<Card> getOpenedCards() {
        return this.getCards();
    }
}
