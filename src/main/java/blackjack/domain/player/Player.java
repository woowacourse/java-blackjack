package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public interface Player {

    int NUMBER_OF_INITIAL_CARDS = 2;

    void initializeCards(final Deck deck);

    void drawCard(final Deck deck);

    boolean isBust();

    boolean isBlackJack();

    boolean isTwentyOne();

    boolean isSameName(Player player);

    Cards cards();

    String name();

    Score score();

    Money money();
}
