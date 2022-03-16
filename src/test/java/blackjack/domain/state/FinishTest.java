package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FinishTest {

    @Test
    void 최종_수익_계산() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, A)));
        final BlackjackGameState stand = new Stand(cards);

        final Cards compareCards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final BlackjackGameState compareStand = new Stand(compareCards);

        assertThat(stand.profit(1000, compareStand)).isEqualTo(1000);
    }
}
