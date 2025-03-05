package blackjack.domian;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player(List.of(), scoreCalculator), new Player(List.of(), scoreCalculator),
                new Player(List.of(), scoreCalculator), new Player(List.of(), scoreCalculator),
                new Player(List.of(), scoreCalculator), new Player(List.of(), scoreCalculator),
                new Player(List.of(), scoreCalculator), new Player(List.of(), scoreCalculator),
                new Player(List.of(), scoreCalculator), new Player(List.of(), scoreCalculator),
                new Player(List.of(), scoreCalculator)
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최대 10명입니다.");
    }

    @Test
    void 플레이어_수가_최소_2명을_넘지_않으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player(List.of(), scoreCalculator)
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }
}
