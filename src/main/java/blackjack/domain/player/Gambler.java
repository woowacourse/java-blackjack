package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;

import java.util.List;

public class Gambler extends Player {
    private final BetAmount betAmount;

    public Gambler(PlayerName name, BetAmount betAmount, Cards cards) {
        super(name, cards);
        this.betAmount = betAmount;
    }

    @Override
    public List<Card> getOpenedCards() {
        return this.getCards();
    }

    public Profit getProfit(GameResult gameResult) {
        return Profit.calculateFrom(betAmount.amount(), gameResult);
    }
}
