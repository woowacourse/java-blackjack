package blackjack.domain.participant;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    void 블랙잭_게임에_참여하는_플레이어들을_알_수_있다() {
        final Players players = 플레이어들("pobi", "jason");

        assertThat(players).extracting("players", InstanceOfAssertFactories.list(Player.class))
                .extracting("name")
                .containsExactly("pobi", "jason");
    }

    @Test
    void 플레이어_이름_중_중복된_이름을_가지면_예외가_발생한다() {
        assertThatThrownBy(() -> 플레이어들("pobi", "pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름 중 중복된 이름이 존재할 수 없습니다.");
    }

    @Test
    void 게임에_참여하는_플레이어의_수가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("게임에 참여하는 플레이어는 1 ~ 6명이어야 합니다.");
    }

    @Test
    void 게임에_참여하는_플레이어의_수가_6보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> 플레이어들("11", "22", "33", "44", "55", "66", "77"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("게임에 참여하는 플레이어는 1 ~ 6명이어야 합니다.");
    }
}
