package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static model.UserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    Player bustPlayer = new Player(PLAYER_NAME, Arrays.asList(
            new Card(Symbol.KING, Type.DIAMOND),
            new Card(Symbol.QUEEN, Type.DIAMOND),
            new Card(Symbol.TWO, Type.DIAMOND)
    ));
    Player notBustPlayer = new Player(PLAYER_NAME, Arrays.asList(
            new Card(Symbol.KING, Type.DIAMOND),
            new Card(Symbol.QUEEN, Type.DIAMOND),
            new Card(Symbol.ACE, Type.DIAMOND)
    ));
    Player blackJackPlayer = new Player(PLAYER_NAME, Arrays.asList(
            new Card(Symbol.QUEEN, Type.CLUB),
            new Card(Symbol.ACE, Type.HEART)
    ));
    Dealer bustDealer = new Dealer(Arrays.asList(
            new Card(Symbol.KING, Type.DIAMOND),
            new Card(Symbol.QUEEN, Type.DIAMOND),
            new Card(Symbol.TWO, Type.DIAMOND)
    ));
    Dealer notBustDealer = new Dealer(Arrays.asList(
            new Card(Symbol.KING, Type.DIAMOND),
            new Card(Symbol.QUEEN, Type.DIAMOND),
            new Card(Symbol.ACE, Type.DIAMOND)
    ));
    Dealer blackJackDealer = new Dealer(Arrays.asList(
            new Card(Symbol.QUEEN, Type.CLUB),
            new Card(Symbol.ACE, Type.HEART)
    ));

    @Test
    @DisplayName("플레이어만 첫 패가 21인 경우 BLACKJACK")
    void blackJackTest() {
        assertThat(Result.compete(notBustDealer, blackJackPlayer)).isEqualTo(Result.BLACKJACK);
        assertThat(Result.compete(bustDealer, blackJackPlayer)).isEqualTo(Result.BLACKJACK);

    }

    @DisplayName("딜러와 플레이어 모두 21을 넘긴 경우 LOSE")
    @Test
    void bothBustTest() {
        assertThat(Result.compete(bustDealer, bustPlayer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러만 21을 넘긴 경우 플레이어 WIN")
    void dealerBustTest() {
        assertThat(Result.compete(bustDealer, notBustPlayer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 21인 경우 DRAW")
    void bothBlackJackTest() {
        assertThat(Result.compete(blackJackDealer, blackJackPlayer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭인 경우 플레이어 Lose")
    void dealerBlackJackTest() {
        assertThat(Result.compete(blackJackDealer, notBustPlayer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("블랙잭 우승 시 수익 계산")
    void playerBlackJackRevenueTest() {
        Result result = Result.compete(notBustDealer, blackJackPlayer);
        assertThat(result.calculateProfit(blackJackPlayer).getProfit()).isEqualTo(150);
    }
}
