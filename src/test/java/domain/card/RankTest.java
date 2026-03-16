package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RankTest {
    @Test
    void 에이스이면_참을_반환해야_한다() {
        Rank ace = Rank.ACE;
        Assertions.assertThat(ace.isAce()).isTrue();
    }

    @Test
    void 에이스이면_거짓을_반환해야_한다() {
        Rank ace = Rank.TEN;
        Assertions.assertThat(ace.isAce()).isFalse();
    }
}
