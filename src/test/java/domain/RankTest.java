package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("인덱스로 카드 번호를 찾아 반환한다.")
    @Test
    void findNumberByIndex() {
        // given
        int kingIndex = 11;
        Rank expected = Rank.KING;

        // when
        Rank rank = Rank.findBy(kingIndex);

        // then
        assertThat(rank).isEqualTo(expected);
    }
}
