package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
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
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Dealer dealer = Dealer.of();

        // when
        dealer.receive(cardDeck.popCard());

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }
}
