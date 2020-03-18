package blackjack.domain.gambler;

import blackjack.domain.card.CardDeck;

public interface Gambler {

    void drawCard(CardDeck cardDeck, int cardCount);

    boolean canDrawCard();
}
