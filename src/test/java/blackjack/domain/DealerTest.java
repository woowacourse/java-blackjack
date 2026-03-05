package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러 생성 테스트")
    void 딜러_생성_테스트() {
        // when & then
        assertThatCode(() -> new Dealer(new Deck()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("한 장씩 카드를 꺼내는 테스트")
    void 한_장씩_카드를_꺼내는_테스트() {
        // given
        Dealer dealer = new Dealer(new Deck());

        // when & then
        assertThatCode(dealer::bringCard)
                .doesNotThrowAnyException();
    }

}
