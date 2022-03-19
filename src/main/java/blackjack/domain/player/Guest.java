package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import blackjack.domain.strategy.HitStrategy;
import java.util.ArrayList;

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
        return hitStrategy.getHitFlag(this);
    }
}
