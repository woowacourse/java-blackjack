package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.player.info.Name;

import java.util.List;

import blackjack.domain.result.prize.PrizeMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Test
    @DisplayName("게임 플레이어 결과 리스트와 딜러 결과를 통해 전체 결과를 생성한다.")
    void create_with_gamePlayerResultList_and_dealerResult() {
        final Name name = new Name("초롱");
        final List<GamePlayerResult> gamePlayerResults = List.of(new GamePlayerResult(name, new PrizeMoney(2000)));
        final DealerResult dealerResult = DealerResult.of(new Name("딜러"), gamePlayerResults);

        assertThatCode(() -> {
            new Result(gamePlayerResults, dealerResult);
        }).doesNotThrowAnyException();
    }
}
