package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;

public class InitialState extends State {

    private static final int PICK_COUNT = 2;
    public static final int EMPTY = 0;

    public InitialState(HandCard handCard) {
        super(handCard);
        validateHandCardIsEmpty(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            handCard.add(picked);
        }
        if (handCard.isBigScoreEqual(BLACKJACK_NUMBER)) {
            return new BlackjackState(handCard);
        }
        return new DrawState(handCard);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    private void validateHandCardIsEmpty(HandCard handCard) {
        if (handCard.size() != EMPTY) {
            throw new IllegalArgumentException("초기 상태의 카드는 비어있어야 합니다.");
        }
    }
}
