package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResult;

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

    public Profit getProfit(GameResult gameResult) {
        return betAmount.getProfit(gameResult);
    }
}
