package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러_생성_성공() {
        // given
        CardDeck cardDeck = CardDeck.of();

        // when & then
        assertThatCode(() -> Dealer.of(cardDeck))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어_한_명에게_카드_한_장을_준다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);

        // when & then
        assertThatCode(() -> dealer.passCard())
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_받는다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        Card card = dealer.passCard();

        // when
        dealer.receive(card);

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }
}
