package blackjack.domain.participant2;

import blackjack.domain.card.Card;
import blackjack.domain.hand.CardHand;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy2;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public interface Participant {

    List<Card> openInitialCards();

    void drawAll(final HitOrStayChoiceStrategy hitOrStay,
                 final CardsViewStrategy2 viewStrategy,
                 final CardSupplier supplier);

    String getName();

    CardHand getHand();
}