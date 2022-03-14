package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public interface Player {

    void addCard(Card card);

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
