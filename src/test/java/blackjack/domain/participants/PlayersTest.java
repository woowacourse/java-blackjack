package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Cards;
import blackjack.domain.card.ScoreCalculator;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player("poba", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobg", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobb", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobh", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobc", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobi", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobd", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobj", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobe", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobk", new Cards(List.of(), new ScoreCalculator())),
                new Player("pobf", new Cards(List.of(), new ScoreCalculator()))
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
                new Player("pobi", new Cards(List.of(), new ScoreCalculator()))
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }
}
