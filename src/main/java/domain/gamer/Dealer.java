package domain.gamer;

import domain.deck.Card;
import domain.profit.Profit;
import domain.state.State;
import java.util.List;

public class Dealer extends Gamer {

    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    public Dealer(final Nickname nickname, final State state) {
        super(nickname, state);
    }

    @Override
    public List<Card> getInitialCards() {
        final List<Card> cards = getCards();
        return List.of(cards.getFirst());
    }

    public Profit evaluateResult(final Gamer gamer) {
        final int result = gamer.compareTo(this);

        if (isDraw(result)) {
            return Profit.DRAW;
        }
        if (isLose(result)) {
            return Profit.LOSE;
        }
        if (isWin(result) && gamer.isBlackjack()) {
            return Profit.WIN_BLACKJACK;
        }
        return Profit.WIN;
    }

    private boolean isWin(final int result) {
        return result == WIN;
    }

    private boolean isDraw(final int result) {
        return result == DRAW;
    }

    private boolean isLose(final int result) {
        return result == LOSE;
    }
}
