package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.card.Denomination;
import org.junit.jupiter.api.Test;

class HitTest {
    @Test
    void 카드를_추가했을_때_bust가_되면_bust_상태를_리턴한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.JACK));
        cards.addCard(카드(Denomination.TEN));
        CardState hit = new Hit(cards);

        CardState bust = hit.receive(카드(Denomination.TWO));

        assertThat(bust).isExactlyInstanceOf(Bust.class);
    }

    @Test
    void 카드를_추가했을_때_blackjack이_되면_blackjack_상태를_리턴한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        CardState hit = new Hit(cards);

        CardState blackjack = hit.receive(카드(Denomination.TEN));

        assertThat(blackjack).isExactlyInstanceOf(Blackjack.class);
    }

    @Test
    void 카드를_추가했을_때_더_추가할_수_있으면_현재_상태를_리턴한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.JACK));
        CardState hit = new Hit(cards);

        CardState next = hit.receive(카드(Denomination.TEN));

        assertThat(next).isExactlyInstanceOf(Hit.class);
    }
}
