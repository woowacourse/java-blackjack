package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardFactoryTest {

    @Test
    void 총_52장의_카드를_생성할_수_있다() {
        final List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
    }
}
