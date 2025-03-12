package blackjack.model.cardMachine;

import blackjack.model.card.Card;
import java.util.List;

public interface CardMachine {

    void receiveDeck(List<Card> deck);

    Card drawOneCard();
}
