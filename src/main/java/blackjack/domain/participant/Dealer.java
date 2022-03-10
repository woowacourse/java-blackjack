package blackjack.domain.participant;

import static blackjack.domain.card.Cards.BLACK_JACK_NUMBER;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameOutcome;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 17;

    public Dealer(final List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public List<Card> initCards() {
        return state.cards().firstCard();
    }

    @Override
    public boolean canDraw() {
        return state.cards().calculateScore() < DEALER_LIMIT_SCORE;
    }

    public boolean isBust() {
        return state.cards().calculateScore() > BLACK_JACK_NUMBER;
    }

    public List<Card> getCards() {
        validateEndTurn();
        return state.cards().values();
    }

    private void validateEndTurn() {
        if (canDraw()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
    }

    public GameOutcome judgeOutcomeOfPlayer(final Player player) {
        return player.state.compare(this.state);
    }
}
