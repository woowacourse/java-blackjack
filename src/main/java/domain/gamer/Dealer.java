package domain.gamer;

import domain.deck.Card;
import domain.profit.Profit;
import domain.state.State;
import java.util.List;

public class Dealer extends Gamer {

    private static final int WIN = 1;
    private static final int DRAW = 0;

    public Dealer(final Nickname nickname, final State state) {
        super(nickname, state);
    }

    @Override
    public List<Card> getInitialCards() {
        final Hand hand = getHand();
        final List<Card> cards = hand.getCards();
        return List.of(cards.getFirst());
    }

    public Profit evaluateResult(final Gamer gamer) {
        final int result = gamer.compareTo(this);

        if (isWin(result)) {
            if (gamer.isBlackjack()) {
                return Profit.WIN_BLACKJACK;
            }
            return Profit.WIN;
        }
        if (isDraw(result)) {
            return Profit.DRAW;
        }
        return Profit.LOSE;
    }

    private boolean isWin(final int result) {
        return result == WIN;
    }

    private boolean isDraw(final int result) {
        return result == DRAW;
    }
}
