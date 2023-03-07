package blackjack.domain.user;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_LIMIT = 16;

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public List<Card> getInitialStatus() {
        return List.of(getStatus().get(0));
    }

    public GameResult comparePlayer(final Player player) {
        if (this.isBust()) {
            return compareByBust(player);
        }
        return compareByScore(player);
    }

    private GameResult compareByBust(final Player player) {
        if (player.isBust()) {
            return GameResult.TIE;
        }
        return GameResult.WIN;
    }

    private GameResult compareByScore(final Player player) {
        if (this.getScore() > player.getScore()) {
            return GameResult.LOSE;
        }
        if (this.getScore() == player.getScore()) {
            return GameResult.TIE;
        }
        return GameResult.WIN;
    }

    public boolean isOverDraw() {
        return this.getScore() > DEALER_DRAW_LIMIT;
    }
}
