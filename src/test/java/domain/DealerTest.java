package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러_생성_성공() {
        // given nothing

        // when & then
        assertThatCode(() -> Dealer.of())
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_받는다() {
        // given
        Dealer dealer = Dealer.of();
        Card card = Card.of(TrumpNumber.NINE, TrumpShape.CLUB);

        // when
        dealer.receive(card);

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }
}
