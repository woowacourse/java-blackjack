package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    private final HankRank BLACKJACK = new Blackjack();

    @DisplayName("해당 핸드 랭크는 블랙잭이다.")
    @Test
    void isBlackjackTest() {

        assertThat(BLACKJACK.isBlackjack()).isTrue();
    }

    @DisplayName("해당 핸드 랭크는 버스트가 아니다.")
    @Test
    void isBustTest() {

        assertThat(BLACKJACK.isBust()).isFalse();
    }
}
