package blackjack.domain.result;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static blackjack.domain.result.ResultType.LOSE;
import static blackjack.domain.result.ResultType.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersResultsTest {
    @DisplayName("예외 테스트: 생성자에 null 값 전달된 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new PlayersResults(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("딜러 게임 결과 확인")
    @Test
    void test2() {
        List<PlayerResult> results = new ArrayList<>();
        results.add(new PlayerResult(new Player(new Name("포비")), LOSE));
        results.add(new PlayerResult(new Player(new Name("쪼밀리")), LOSE));
        results.add(new PlayerResult(new Player(new Name("타미")), LOSE));
        results.add(new PlayerResult(new Player(new Name("CU")), WIN));


        PlayersResults playersResults = new PlayersResults(results);

        String actualRecord = playersResults.showDealerRecord();

        String expectedRecord = "3승 1패";

        assertThat(actualRecord).isEqualTo(expectedRecord);
    }

    @DisplayName("딜러 수익 확인")
    @Test
    void test3() {
        List<PlayerResult> results = new ArrayList<>();
        results.add(new PlayerResult(new BettingPlayer(new Name("포비"), new Money(1000)), LOSE));
        results.add(new PlayerResult(new BettingPlayer(new Name("쪼밀리"), new Money(1000)), LOSE));
        results.add(new PlayerResult(new BettingPlayer(new Name("타미"), new Money(1000)), LOSE));
        results.add(new PlayerResult(new BettingPlayer(new Name("CU"), new Money(1000)), WIN));

        PlayersResults playersResults = new PlayersResults(results);

        double actualProfit = playersResults.computeDealerProfit();

        double expectedProfit = 2000;

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}
