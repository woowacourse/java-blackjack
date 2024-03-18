package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.Status;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    public Card getFirstCard() {
        try {
            return getCards().get(0);
        } catch (IndexOutOfBoundsException entry) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }

    public Status judgePlayerStatus(final Player player) {
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

    @Override
    public boolean isDrawable() {
        return this.getScore() <= HIT_THRESHOLD;
    }
}
