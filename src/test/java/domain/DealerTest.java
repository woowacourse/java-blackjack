package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드가 기준을 넘으면 true를 반환한다.")
    @Test
    void 딜러의_카드가_기준을_넘으면_true를_반환한다() {

        // given
        Dealer dealer = new Dealer(new Nickname("딜러"));
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.KING, Shape.CLOVER));

        // when
        boolean expected = dealer.isMoreThanThreshold();

        // then
        assertThat(expected).isTrue();
    }
}
