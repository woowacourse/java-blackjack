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
        Player player = Player.of("pobi");
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);

        // when
        dealer.passCard(player);

        // then
        assertThat(player.getCards()).hasSize(1);
    }
}
