package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.utils.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("승패에 따라 결과를 저장한다.")
    @Test
    void test1() {
        // given
        Player player = new Player("꾹이", HandFixture.createHandWithOptimisticValue20());
        Result result = new Result();

        result.save(player, GameResultType.WIN);

        assertAll(() -> assertThat(result.getPlayersResult()).containsEntry(player, GameResultType.WIN),
                () -> assertThat(result.getDealerResult()).containsEntry(GameResultType.LOSE, 1)
        );
    }
}
