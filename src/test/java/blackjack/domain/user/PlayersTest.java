package blackjack.domain.user;

import blackjack.domain.Money;
import blackjack.domain.ResultType;
import blackjack.domain.result.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private final Money defaultMoney = Money.of(0);
    private Players players;
    private Player player1;
    private Player player2;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        players = Players.of(Arrays.asList(new Player(Name.of("youngE"), defaultMoney), new Player(Name.of("kimkim"), defaultMoney)));
        player1 = players.players().get(0);
        player2 = players.players().get(1);
        player1.draw(ace);
        player1.draw(jack);
        // 플레이어 youngE에게 블랙잭을 준다.
        player2.draw(jack);
        player2.draw(six);
        // 플레이어 Kimkim에게 16을 준다.
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭일 때")
    @Test
    void checkWinOrLoseWhenDealerHasBlackJackTest() {
        dealer.draw(ace);
        dealer.draw(jack);
        Results results = players.generateResultsMapAgainstDealer(dealer);

        assertThat(results.getResultOf(player1)).isEqualTo(ResultType.DRAW);

        assertThat(results.getResultOf(player2)).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭이 아니었을 때")
    @Test
    void checkWinOrLoseWhenDealerHasNotBlackJackTest() {
        dealer.draw(ace);
        dealer.draw(seven);
        Results results = players.generateResultsMapAgainstDealer(dealer);

        assertThat(results.getResultOf(player1)).isEqualTo(ResultType.WIN);

        assertThat(results.getResultOf(player2)).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러와 플레이어가 버스트일 때")
    @Test
    void checkWinOrLoseTestWhenBothBust() {
        dealer.draw(six);
        dealer.draw(seven);
        dealer.draw(jack);
        player2.draw(seven); // player2 에게 7을 추가로 주어 23을 만들어 버스트 상태로 만든다.
        Results results = players.generateResultsMapAgainstDealer(dealer);

        assertThat(results.getResultOf(player1)).isEqualTo(ResultType.WIN);

        assertThat(results.getResultOf(player2)).isEqualTo(ResultType.LOSE);
    }

}
