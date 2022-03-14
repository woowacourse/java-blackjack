package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer extends AbstractParticipant {

    private static final String NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 17;
    private static final int FIRST_HIT_CARD_SIZE = 1;

    private Dealer(final String name, final Cards cards, final ParticipantStatus participantStatus) {
        super(name, cards, participantStatus);
    }

    public static Dealer createNewDealer(final Cards cards) {
        if (cards.isBlackJack()) {
            return new Dealer(NAME, cards, ParticipantStatus.BLACKJACK);
        }
        if (cards.calculateMaxScore() >= DEALER_LIMIT_SCORE) {
            return new Dealer(NAME, cards, ParticipantStatus.FINISHED);
        }
        return new Dealer(NAME, cards, ParticipantStatus.RUNNING);
    }

    @Override
    public List<Card> firstCards() {
        return List.copyOf(super.cards().subList(0, FIRST_HIT_CARD_SIZE));
    }

    @Override
    public void changeFinishStatus() {
        throw new IllegalStateException("딜러는 직접 게임을 종료할 권한이 없습니다.");
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public List<Card> cards() {
        validateEndTurn();
        return super.cards();
    }

    private void validateEndTurn() {
        if (canHit()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
    }

    @Override
    boolean isEnd() {
        return super.calculateScore() >= DEALER_LIMIT_SCORE;
    }
}
