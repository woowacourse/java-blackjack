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
}
