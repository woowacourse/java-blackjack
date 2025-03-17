package Blackjack.domain.participant;

import Blackjack.domain.Bet;
import Blackjack.domain.card.Card;
import Blackjack.domain.game.GameStatus;
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

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }

    @Override
    public boolean ableToAddCard() {
        return !isBust();
    }

    protected GameStatus determineGameStatus(final Dealer dealer) {
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

    public int calculateProfit(final Dealer dealer) {
        GameStatus gameStatus = determineGameStatus(dealer);
        double profit = gameStatus.calculateBetResult(bet);
        return (int) Math.ceil(profit);
    }
}
