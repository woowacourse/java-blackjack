package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러_생성_성공() {
        // given nothing

        // when & then
        assertThatCode(() -> Dealer.of(CardDeck.of()))
                .doesNotThrowAnyException();
    }

    @Disabled
    @Test
    void 카드를_한_장_받는다() {
        // given
        Dealer dealer = Dealer.of(CardDeck.of());
        Card card = Card.of(TrumpNumber.NINE, TrumpShape.CLUB);

        // when
        dealer.receive();

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }
}
