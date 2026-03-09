package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드_뽑기_테스트() {
        // when
        Card card = Deck.pop();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }

}
