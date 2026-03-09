package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어가_5명이_넘을_경우_예외_발생() {
        // given
        List<String> playersName = List.of("1,2,3,4,5,6".split(","));

        // when & then
        assertThatThrownBy(() -> Players.from(playersName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어끼리_이름이_동일할_경우_예외_발생() {
        // given
        List<String> playersName = List.of("1,1,3,4,5".split(","));

        // when & then
        assertThatThrownBy(() -> Players.from(playersName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어끼리_이름_중_딜러와_동일한_이름이_있는_경우_예외_발생() {
        // given
        List<String> playersName = List.of("1,2,3,4,딜러".split(","));

        // when & then
        assertThatThrownBy(() -> Players.from(playersName))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
