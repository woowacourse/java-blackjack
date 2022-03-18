package blackjack.domain.participant;

import static blackjack.domain.card.Cards.BLACK_JACK_NUMBER;

import blackjack.domain.card.Card;
import blackjack.domain.game.BettingMoney;
import blackjack.domain.participant.vo.ParticipantName;
import blackjack.domain.state.State;
import java.util.List;

public class Dealer extends Participant {

    private static final ParticipantName DEALER_NAME = new ParticipantName("딜러");
    private static final int DEALER_LIMIT_SCORE = 17;

    private Dealer(final State state) {
        super(DEALER_NAME, state);
    }

    public Dealer(final List<Card> cards) {
        super(DEALER_NAME, BettingMoney.getZeroBettingMoney(), cards);
    }

    @Override
    public List<Card> getInitCards() {
        return state.cards().getFirstCard();
    }

    @Override
    public boolean canDraw() {
        return state.cards().getScore() < DEALER_LIMIT_SCORE;
    }

    public boolean isBust() {
        return state.cards().getScore() > BLACK_JACK_NUMBER;
    }

    public Dealer stay() {
        state = state.stay();
        return new Dealer(state);
    }

    public Dealer draw(final Card card) {
        state = state.draw(card);
        return new Dealer(state);
    }

    @Override
    public List<Card> getCards() {
        validateEndTurn();
        return state.cards().getValues();
    }

    private void validateEndTurn() {
        if (canDraw()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
    }

    public int calculateProfitOf(final Player player) {
        return player.state.getProfit(this.state);
    }
}
