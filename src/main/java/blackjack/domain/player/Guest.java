package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;
import blackjack.domain.strategy.HitStrategy;

public class Guest extends AbstractPlayer {
    private final HitStrategy hitStrategy;

    public Guest(String name, Deck deck, HitStrategy hitStrategy) {
        super(name, Ready.start(deck.pick(), deck.pick()));
        this.hitStrategy = hitStrategy;
    }

    @Override
    public Cards getShowCards() {
        return getCards();
    }

    @Override
    public HitFlag checkHitFlag() {
        HitFlag hitFlag = hitStrategy.getHitFlag(this);
        if (hitFlag == HitFlag.N) {
            state = state.stand();
        }
        return hitFlag;
    }

    @Override
    public void judge(Player dealer) {
        state = state.judge(dealer.getState());
    }
}
