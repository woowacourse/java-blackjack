package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.CardHand;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public interface Participant {

    List<Card> openInitialCards();

    void drawAll(final HitOrStayChoiceStrategy hitOrStay,
                 final CardsViewStrategy viewStrategy,
                 final CardSupplier supplier);

    String getName();

    CardHand getHand();
}