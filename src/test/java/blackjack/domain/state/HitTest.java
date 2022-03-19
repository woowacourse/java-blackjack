package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.EIGHT)));

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Bust가 되는 경우 테스트")
    void hitAndIsBust() {
        final Status status = new Hit(cards)
                .draw(Card.from(Suit.SPADE, Denomination.EIGHT));

        assertThat(status).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Hit이 되는 경우 테스트")
    void hitAndIsHit() {
        final Status status = new Hit(cards)
                .draw(Card.from(Suit.SPADE, Denomination.TWO));

        assertThat(status).isInstanceOf(Hit.class);
    }
}