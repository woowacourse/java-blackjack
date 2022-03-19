package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private final Ready ready = new Ready();

    @Test
    @DisplayName("카드가 한장만 있을 때 Ready인지 테스트")
    void drawOneCard() {
        final Status status = ready.draw(Card.from(Suit.CLOVER, Denomination.EIGHT));

        Assertions.assertThat(status).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("카드 두 장을 받을 때 Hit인지 테스트")
    void drawTwoCardsHit() {
        final Status status = ready.draw(Card.from(Suit.CLOVER, Denomination.EIGHT))
                .draw(Card.from(Suit.CLOVER, Denomination.FIVE));

        Assertions.assertThat(status).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드 두 장을 받을 때 블랙잭인지 테스트")
    void drawTwoCardsBlackjack() {
        final Status status = ready.draw(Card.from(Suit.CLOVER, Denomination.ACE))
                .draw(Card.from(Suit.CLOVER, Denomination.JACK));

        Assertions.assertThat(status).isInstanceOf(Blackjack.class);
    }
}