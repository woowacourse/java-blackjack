package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public interface Player {

    void receiveCard(final Card card);

    List<Card> openCards();

    List<Card> showCards();

    int calculateResult();

    boolean isReceivable();

    String showName();
}
