package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드_뽑기_테스트() {
        // given

        // when & then
        assertThatCode(Deck::pop)
                .doesNotThrowAnyException();
    }

    // TODO : 랜덤을 테스트 할 수 있도록 변경 필요
    @Test
    void 카드_섞기_테스트() {
        // given

        // when

        // then
        assertThatCode(Deck::shuffle)
                .doesNotThrowAnyException();
    }

}
