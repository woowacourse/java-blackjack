package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public interface Player {

    void initializeCards(Deck deck);

    void receiveCard(Card card);

    Name getName();

    Score getScore();

    Cards getCards();
}
