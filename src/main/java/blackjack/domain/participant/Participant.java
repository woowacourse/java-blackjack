package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {

    List<Card> firstCards();

    void hit(final Card card);

    boolean canHit();

    void changeFinishStatus();

    int calculateResultScore();

    boolean isBlackJack();

    boolean isBust();

    boolean isDealer();

    List<Card> cards();

    String getName();
}
