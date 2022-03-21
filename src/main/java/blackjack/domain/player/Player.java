package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public interface Player {
    boolean isBust();

    boolean isBlackjack();

    boolean isHittable();

    void hit(Card card);

    Cards getShowCards();

    String getName();

    Cards getCards();

    HitFlag checkHitFlag();

    void judge(Player dealer);

    State getState();

    void stand();

    double getPrizeRate();
}
