package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public interface Player {

    void addCard(PlayingCard playingCard);

    boolean isBlackJack();

    boolean isCanHit();

    boolean isBust();

    boolean isDealer();

    boolean isWin(Player guest, Player dealer);

    boolean isLose(int point);

    boolean isDraw(Player competitor);

    String getName();

    PlayingCards getDeck();
}
