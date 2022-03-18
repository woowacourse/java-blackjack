package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public interface Player {

    void addCard(PlayingCard playingCard);

    boolean isBlackJack(Player player);

    boolean isCanHit();

    boolean isBust();

    boolean isDealer();

    boolean isWin(Player player);

    boolean isLose(Player player);

    String getName();

    PlayingCards getPlayingCards();
}
