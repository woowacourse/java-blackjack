package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.Status;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    public boolean isMoreCardNeeded() {
        return this.hand.calculateScore() <= HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        try {
            return hand.getAllCards().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }

    public int findBettingResult(final Player player, final int bettingMoney) {
        Status status = judgePlayerStatus(player);

        if (Status.WIN.equals(status)) {
            return bettingMoney;
        }
        if (Status.PUSH.equals(status)) {
            return 0;
        }
        if (Status.LOSE.equals(status)) {
            return -bettingMoney;
        }
        if (Status.BLACKJACK.equals(status)) {
            return bettingMoney + bettingMoney / 2;
        }

        throw new IllegalStateException("[ERROR] 유효하지 않은 경우의 수 입니다.");
    }

    private Status judgePlayerStatus(final Player player) {
        if (player.isBust()) {
            return Status.LOSE;
        }
        if (this.isBust()) {
            return Status.WIN;
        }
        if (this.getScore() == player.getScore()) {
            return Status.PUSH;
        }
        if (player.isBlackjack()) {
            return Status.BLACKJACK;
        }
        if (this.getScore() < player.getScore()) {
            return Status.WIN;
        }
        return Status.LOSE;
    }
}
