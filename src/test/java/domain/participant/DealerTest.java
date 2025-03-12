package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.CardDeck;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러_생성_성공() {
        // given nothing

        // when & then
        assertThatCode(() -> Dealer.of(CardDeck.of()))
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_받는다() {
        // given
        Dealer dealer = Dealer.of(CardDeck.of());

        // when
        dealer.receive(dealer.drawCard());

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }

    @Test
    void 딜러는_배당_금액을_잃는다() {
        // given
        Dealer dealer = Dealer.of(CardDeck.of());

        // when
        dealer.decreaseTotalAmount(1000, 1.5);

        // then
        assertThat(dealer.getTotalWinnings()).isEqualTo(-1500);
    }
}
