package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ReceivedCardsTest {

    @Test
    void 카드를_받는다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new NormalCard(2, CardShape.CLOVER));
        receivecdCards.receive(new NormalCard(2, CardShape.HEART));

        // then
        assertThat(receivecdCards.size()).isEqualTo(2);
    }
}
