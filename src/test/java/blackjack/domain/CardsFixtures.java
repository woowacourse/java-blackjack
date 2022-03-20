package blackjack.domain;

import static blackjack.domain.CardFixtures.ACE_SPACE;
import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.KING_SPACE;
import static blackjack.domain.CardFixtures.THREE_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;

import blackjack.domain.card.Cards;
import java.util.List;

public class CardsFixtures {

    public static final Cards BLACKJACK = new Cards(List.of(ACE_SPACE, JACK_SPACE));
    public static final Cards HIT = new Cards(List.of(ACE_SPACE, TWO_SPACE));
    public static final Cards TWELVE_HIT = new Cards(List.of(TWO_SPACE, KING_SPACE));
    public static final Cards FIVE_HIT = new Cards(List.of(TWO_SPACE, THREE_SPACE));
}
