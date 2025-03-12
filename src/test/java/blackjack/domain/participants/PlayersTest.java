package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player("poba", new Cards()),
                new Player("pobg", new Cards()),
                new Player("pobb", new Cards()),
                new Player("pobh", new Cards()),
                new Player("pobc", new Cards()),
                new Player("pobi", new Cards()),
                new Player("pobd", new Cards()),
                new Player("pobj", new Cards()),
                new Player("pobe", new Cards()),
                new Player("pobk", new Cards()),
                new Player("pobf", new Cards())
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최대 10명입니다.");
    }

    @Test
    void 플레이어_수가_최소_2명을_넘지_않으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(new Player("pobi", new Cards()));

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }
}
