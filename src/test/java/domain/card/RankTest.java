package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class RankTest {

    @DisplayName("SMALL_ACE를 제외한 13개의 등급들을 반환한다.")
    @Test
    void getRanks() {
        // given
        int expectedSize = 13;

        // when
        List<Rank> ranks = Rank.getRanks();

        // then
        assertThat(ranks).hasSize(expectedSize);
    }

    @DisplayName("등급이 Ace면 true 아니면 false를 반환한다.")
    @Test
    void isAce() {
        // given
        Rank bigAce = Rank.BIG_ACE;
        Rank smallAce = Rank.SMALL_ACE;
        Rank king = Rank.KING;

        // when
        boolean isAce1 = bigAce.isAce();
        boolean isAce2 = smallAce.isAce();
        boolean isAce3 = king.isAce();

        // then
        assertAll(
                () -> assertThat(isAce1).isTrue(),
                () -> assertThat(isAce2).isTrue(),
                () -> assertThat(isAce3).isFalse()
        );
    }
}
