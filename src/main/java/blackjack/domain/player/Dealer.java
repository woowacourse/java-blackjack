package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT_FLAG_SCORE = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer(Deck deck) {
        super(DEALER_NAME, Ready.start(deck.pick(), deck.pick()));
    }

    @Override
    public Cards getShowCards() {
        return new Cards(List.of(getCards().getCardValues().get(FIRST_CARD_INDEX)));
    }

    @Override
    public boolean isHittable() {
        return checkHitFlag() == HitFlag.Y;
    }

    @Override
    public HitFlag checkHitFlag() {
        if (getCards().calculateScore() <= HIT_FLAG_SCORE) {
            return HitFlag.Y;
        }
        if (!isBust()) {
            state = state.stand();
        }
        return HitFlag.N;
    }

    @Override
    public void judge(Player dealer) {
        throw new IllegalStateException("딜러는 참가자처럼 승패를 계산하지 않습니다.");
    }
}
