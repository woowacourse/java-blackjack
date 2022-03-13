package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface Player {
    boolean isBust();

    boolean isMaxScore();

    boolean isBlackjack();

    boolean isHittable();

    void hit(Card card);

    Cards getShowCards();

    String getName();

    Cards getCards();

    HitFlag checkHitFlag();
}
