package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    private static HankRank BUST = new Bust();

    @DisplayName("해당 핸드 랭크는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {

        assertThat(BUST.isBlackjack()).isTrue();
    }

    @DisplayName("해당 핸드 랭크는 버스트이다.")
    @Test
    void isBustTest() {

        assertThat(BUST.isBust()).isFalse();
    }
}
