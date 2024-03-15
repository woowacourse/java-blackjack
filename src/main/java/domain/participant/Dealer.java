package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.result.Status;

import java.util.List;

public class Dealer extends Cards {

    private static final int MIN_SCORE = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return bestSumOfCardScore() <= MIN_SCORE;
    }

    public Status decideStatus(Cards playerCards) {
        if (checkPlayerLose(playerCards)) {
            return Status.LOSE;
        }
        if (checkPlayerBlackjack(playerCards)) {
            return Status.WIN_BLACKJACK;
        }
        if (checkPlayerWin(playerCards)) {
            return Status.WIN;
        }
        return Status.TIE;
    }

    private boolean checkPlayerLose(Cards playerCards) {
        return playerCards.isBurst() || this.isNotBurst() && this.isGreaterThan(playerCards);
    }

    private boolean checkPlayerBlackjack(Cards playerCards) {
        return playerCards.isBlackjack() && (this.isBurst() || playerCards.isGreaterThan(this));
    }

    private boolean checkPlayerWin(Cards playerCards) {
        return this.isBurst() || playerCards.isGreaterThan(this);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "cards=" + cards +
                '}';
    }
}
