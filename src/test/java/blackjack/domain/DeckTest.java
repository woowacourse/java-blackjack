package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱 생성 테스트")
    void 덱_생성_테스트() {
        // when & then
        assertThatCode(Deck::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 꺼내기 테스트")
    void 카드_꺼내기_테스트() {
        // given
        Deck deck = new Deck();

        // when & then
        assertThatCode(() -> deck.bringTopCard())
                .doesNotThrowAnyException();
    }
}
