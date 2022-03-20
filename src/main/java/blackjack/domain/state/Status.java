package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface Status {

    Status draw(Card card);

    Status stay();

    boolean isFinished();

    boolean isRunning();

    Cards getCards();
}
