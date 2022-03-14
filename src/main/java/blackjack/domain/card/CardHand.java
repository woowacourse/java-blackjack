package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.List;

public class CardHand {

    private static final String ALREADY_FINISHED_DRAWING_STATE_EXCEPTION_MESSAGE = "이미 카드 패가 확정된 참여자입니다.";

    private CardBundle cardBundle;
    private CardHandState state;

    private CardHand(final CardBundle cardBundle) {
        this.cardBundle = cardBundle;
        updateState();
    }

    public static CardHand of(final CardBundle cardBundle) {
        return new CardHand(cardBundle);
    }

    public void hit(Card card) {
        validateNotFinished();
        this.cardBundle = cardBundle.addAndGenerate(card);
        updateState();
    }

    public void stay() {
        validateNotFinished();
        state = CardHandState.STAY;
    }

    public List<Card> getCards() {
        return cardBundle.getCards();
    }

    public Score getScore() {
        return cardBundle.getScore();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    private void updateState() {
        this.state = CardHandState.of(cardBundle);
    }

    private void validateNotFinished() {
        if (state.isFinished()) {
            throw new IllegalArgumentException(ALREADY_FINISHED_DRAWING_STATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "CardHand{" +
                "cardBundle=" + cardBundle +
                ", state=" + state +
                '}';
    }
}
