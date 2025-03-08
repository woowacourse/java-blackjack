package blackjack.manager;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.StubPossibleSumCardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultManagerTest {

    // 처리한다 (Player, StatusType)

    // 플레이어 기준으로
    // 플레이어 키 맵 승리 설정해주고
    // 딜러는 이게 승리라면 DealerResult 패배가 적립되도록

    BlackJackResultManager blackJackResultManager;

    @BeforeEach
    void init() {
        blackJackResultManager = new BlackJackResultManager();
    }

    @DisplayName("플레이어의 결과를 판단한다.")
    @Test
    void test3() {
        // given
        Dealer dealer = new Dealer(new StubPossibleSumCardHolder(List.of(15, 20, 21)));
        Player player = new Player("꾹이", new StubPossibleSumCardHolder(List.of(15, 19, 20)));

        // when
        GameResultType gameResultType = blackJackResultManager.decideResultOfPlayer(player, dealer);

        // then
        assertThat(gameResultType).isEqualTo(GameResultType.LOSE);
    }

    @DisplayName("플레이어의 결과를 판단한다.")
    @Test
    void test4() {
        // given
        Dealer dealer = new Dealer(new StubPossibleSumCardHolder(List.of(15, 20, 21)));
        Player player = new Player("꾹이", new StubPossibleSumCardHolder(List.of(15, 19, 21)));

        // when
        GameResultType gameResultType = blackJackResultManager.decideResultOfPlayer(player, dealer);

        // then
        assertThat(gameResultType).isEqualTo(GameResultType.TIE);
    }
}
