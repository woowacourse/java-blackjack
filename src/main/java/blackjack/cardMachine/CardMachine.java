package blackjack.cardMachine;

import blackjack.card.Card;
import java.util.List;

public interface CardMachine {

    void receiveDeck(List<Card> deck);

    Card drawOneCard();
}
