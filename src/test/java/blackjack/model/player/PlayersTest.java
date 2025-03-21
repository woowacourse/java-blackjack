package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 참여자가_최소_2명_최대_8명이_아닌_경우_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Players(List.of(
                new Player("한스1", 1000),
                new Player("한스2", 1000),
                new Player("한스3", 1000),
                new Player("한스4", 1000),
                new Player("한스5", 1000),
                new Player("한스6", 1000),
                new Player("한스7", 1000),
                new Player("한스8", 1000),
                new Player("한스9", 1000)
        ))).hasMessage("참여자는 2~8명 이여야 합니다.");
    }

    @Test
    void 중복된_이름이_있으면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new Players(List.of(
                new Player("프리", 1000),
                new Player("프리", 1000)
        ))).hasMessage("중복된 이름은 사용할 수 없습니다.");
    }
}
