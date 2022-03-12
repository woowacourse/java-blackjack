package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends AbstractParticipant {

    private static final String NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 17;
    private static final int FIRST_HIT_CARD_SIZE = 1;

    private Dealer(final String name, final Cards cards, final GameStatus gameStatus) {
        super(name, cards, gameStatus);
    }

    public static Dealer createNewDealer(final List<Card> cards) {
        final Cards ownCards = new Cards(new ArrayList<>(cards));

        if (ownCards.isBlackJack()) {
            return new Dealer(NAME, ownCards, GameStatus.BLACKJACK);
        } else if (ownCards.calculateMaxScore() >= DEALER_LIMIT_SCORE) {
            return new Dealer(NAME, ownCards, GameStatus.FINISHED);
        }
        return new Dealer(NAME, ownCards, GameStatus.RUNNING);
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
