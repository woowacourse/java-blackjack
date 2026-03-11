package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.deck.Deck;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱_생성_테스트() {
        // when & then
        assertThatCode(Deck::new)
                .doesNotThrowAnyException();
    }

    @Test
    void 카드_꺼내기_테스트() {
        // given
        Deck deck = new Deck();

        // when & then
        assertThatCode(deck::bringTopCard)
                .doesNotThrowAnyException();
    }

}
