package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public interface Player {

    void initializeCards(Deck deck);

    void drawCard(Deck deck);

    Name getName();

    Score getScore();

    Cards getCards();
}
