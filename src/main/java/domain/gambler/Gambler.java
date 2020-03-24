package domain.gambler;

import domain.card.CardDeck;
import domain.card.Cards;
import domain.card.Score;

public interface Gambler {

    void drawCard(CardDeck cardDeck, int cardCount);

    boolean canHit();

    Name getName();

    Score getScore();

    Cards getCards();
}
