package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalRankTest {

    private final HankRank NORMAL_RANK = new NormalRank(20);

    @DisplayName("해당 핸드 랭크는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {

        assertThat(NORMAL_RANK.isBlackjack()).isFalse();
    }

    @DisplayName("해당 핸드 랭크는 버스트가 아니다.")
    @Test
    void isBustTest() {

        assertThat(NORMAL_RANK.isBust()).isFalse();
    }
}
