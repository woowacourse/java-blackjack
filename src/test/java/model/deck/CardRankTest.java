package model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardRankTest {
    @DisplayName("카드가 ace인 경우 최대값을 반환한다.")
    @Test
    void 에이스_카드의_최대값_반환() {
        //given
        CardRank ace = CardRank.ACE;

        //when, then
        assertThat(ace.getMaxValue()).isEqualTo(11);
    }

    @DisplayName("카드가 ace가 아닌 경우 최대값 반환 시 예외를 반환한다..")
    @Test
    void 에이스가_아닌_카드의_최대값_반환() {
        //given
        CardRank notAce = CardRank.FIVE;

        //when, then
        assertThatThrownBy(notAce::getMaxValue)
                .isInstanceOf(IllegalStateException.class);
    }
}
