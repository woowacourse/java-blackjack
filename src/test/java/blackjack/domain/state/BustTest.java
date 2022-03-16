package blackjack.domain.state;

import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    void 수익률이_마이너스1() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK)));
        final Bust bust = new Bust(cards, cards.score());

        assertThat(bust.earningRate(bust)).isEqualTo(-1);
    }
}
