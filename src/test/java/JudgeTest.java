import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeTest {

    private Judge judge;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        judge = new Judge();
        dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.FIVE, CardShape.HEART))));
    }

    @DisplayName("플레이어가 딜러를 상대로 승리를 판단한다.")
    @Test
    void decidePlayerWinByDealer() {
        Player player = new Player("player", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.HEART))));
        judge.decidePlayerResult(player, dealer);
        assertThat(judge.getResult().get(player)).isEqualTo(WinState.WIN);
    }

    @DisplayName("플레이어가 딜러를 상대로 패배를 판단한다.")
    @Test
    void decidePlayerLoseByDealer() {
        Player player = new Player("player", new PlayerCards(List.of(new Card(CardNumber.TWO, CardShape.HEART))));
        judge.decidePlayerResult(player, dealer);
        assertThat(judge.getResult().get(player)).isEqualTo(WinState.LOSE);
    }

    @DisplayName("플레이어가 딜러를 상대로 무승부를 판단한다.")
    @Test
    void decidePlayerDrawByDealer() {
        Player player = new Player("player", new PlayerCards(List.of(new Card(CardNumber.FIVE, CardShape.HEART))));
        judge.decidePlayerResult(player, dealer);
        assertThat(judge.getResult().get(player)).isEqualTo(WinState.DRAW);
    }

    @DisplayName("딜러의 모든 승리 패배 무승부 상태를 반환한다.")
    @Test
    void decideDealerResult() {
        Player player1 = new Player("player1", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.HEART))));
        Player player2 = new Player("player2", new PlayerCards(List.of(new Card(CardNumber.THREE, CardShape.CLOVER))));
        Player player3 = new Player("player3", new PlayerCards(List.of(new Card(CardNumber.FIVE, CardShape.HEART))));

        judge.decidePlayerResult(player1, dealer);
        judge.decidePlayerResult(player2, dealer);
        judge.decidePlayerResult(player3, dealer);
        Map<WinState, Integer> dealerResult = judge.decideDealerResult();

        assertThat(dealerResult.get(WinState.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(WinState.LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(WinState.DRAW)).isEqualTo(1);
    }
}
