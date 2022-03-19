package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public abstract class AbstractPlayer implements Player {
    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";

    private final String name;
    protected State state;

    public AbstractPlayer(String name, State state) {
        validate(name);
        this.name = name;
        this.state = state;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isBust() {
        return state.isBust();
    }

    @Override
    public boolean isHittable() {
        return state.isHitTurn() && checkHitFlag() == HitFlag.Y;
    }

    @Override
    public void hit(Card card) {
        state.hit(card);
    }

    @Override
    public Cards getCards() {
        return state.getCards();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }
}
