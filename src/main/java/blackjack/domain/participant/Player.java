package blackjack.domain.participant;

import blackjack.domain.Bet;
import blackjack.domain.card.Card;
import blackjack.domain.game.GameStatus;
import java.util.List;

public class Player extends Participant {
    private Bet bet;

    public Player(final String name, final Bet bet) {
        super(name);
        this.bet = bet;
    }

    public void increaseBet(final int value) {
        bet = bet.increase(value);
    }

    public int calculateProfit(final Dealer dealer) {
        GameStatus gameStatus = determineGameStatus(dealer);
        double profit = gameStatus.calculateBetResult(bet);
        return (int) Math.ceil(profit);
    }

    public GameStatus determineGameStatus(final Dealer dealer) {
        if (isBlackjack() && !dealer.isBlackjack()) {
            return GameStatus.BLACKJACK;
        }
        if (isBust()) {
            return GameStatus.LOSE;
        }
        if (dealer.isBust() || cards.calculateScore() > dealer.cards.calculateScore()) {
            return GameStatus.WIN;
        }
        if (cards.calculateScore() < dealer.cards.calculateScore()) {
            return GameStatus.LOSE;
        }
        return GameStatus.TIE;
    }

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }

    @Override
    public boolean ableToAddCard() {
        return !isBust();
    }
}
