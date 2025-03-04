package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Dealer dealer = new Dealer();
        String expected = "딜러";

        // when
        String nickname = dealer.getNickname();

        // then
        Assertions.assertThat(nickname).isEqualTo(expected);
    }
}