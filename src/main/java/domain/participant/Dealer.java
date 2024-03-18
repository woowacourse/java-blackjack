package domain.participant;

import domain.card.Card;
import domain.result.Status;

import java.util.List;
import java.util.NoSuchElementException;

public class Dealer extends Participant {

    private static final int MIN_SCORE = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return bestSumOfCardScore() <= MIN_SCORE;
    }

    public Status decideStatus(Player player) {
        if (checkPlayerLose(player)) {
            return Status.LOSE;
        }
        if (checkPlayerBlackjack(player)) {
            return Status.WIN_BLACKJACK;
        }
        if (checkPlayerWin(player)) {
            return Status.WIN;
        }
        return Status.TIE;
    }

    private boolean checkPlayerLose(Player player) {
        return player.isBurst() || this.isNotBurst() && this.isGreaterThan(player);
    }

    private boolean checkPlayerBlackjack(Player player) {
        return player.isBlackjack() && (this.isBurst() || player.isGreaterThan(this));
    }

    private boolean checkPlayerWin(Player player) {
        return this.isBurst() || player.isGreaterThan(this);
    }

    public Card getFirstCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("패에 가지고 있는 카드가 없습니다.");
        }
        return cards.get(0);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "cards=" + cards +
                '}';
    }
}
