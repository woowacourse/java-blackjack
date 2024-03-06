package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("에이스기 참임을 확인한다.")
    void isAceSuccessTest() {
        Assertions.assertThat(Rank.ACE.isAce()).isTrue();
    }

    @Test
    @DisplayName("에이스기 거짓임을 확인한다.")
    void isAceFailTest() {
        Assertions.assertThat(Rank.SIX.isAce()).isFalse();
    }
}
