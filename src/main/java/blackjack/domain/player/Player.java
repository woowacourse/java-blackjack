package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Deck;

public interface Player {

    void addCard(PlayingCard playingCard);

    boolean isBlackJack();

    boolean isOverMoreCardLimit();

    boolean isOverPointLimit();

    boolean isDealer();

    boolean isWin(Player guest, Player dealer);

    boolean isLose(int point);

    boolean isDraw(Player competitor);

    String getName();

    Deck getDeck();
}
