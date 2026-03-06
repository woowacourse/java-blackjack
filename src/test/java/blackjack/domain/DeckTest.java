package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    // TODO : 랜덤을 테스트 할 수 있도록 변경 필요
    @Test
    void 카드_섞기_테스트() {
        // given
        Deck deck = new Deck();
        Deck compareDeck = new Deck();

        // when
        deck.shuffle();

        // then
        assertThat(deck.bringTopCard().getName())
                .isNotEqualTo(compareDeck.bringTopCard().getName());
    }

}
