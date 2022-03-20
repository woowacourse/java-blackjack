package blackjack;

import blackjack.domain.card.HoldCards;
import blackjack.domain.result.PlayerOutcome;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerOutcomeTest {
    HoldCards player22_3;
    HoldCards player21_3;
    HoldCards player21_2;
    HoldCards player20_3;
    HoldCards dealer22_3;
    HoldCards dealer21_3;
    HoldCards dealer21_2;
    HoldCards dealer20_3;

    @BeforeAll
    void setCard() {
        player22_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_THREE));
        player22_3.addCard(SPADE_NINE);
        player21_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_TWO));
        player21_3.addCard(SPADE_NINE);
        player21_2 = HoldCards.init(List.of(SPADE_TEN, SPADE_ACE));
        dealer20_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_TWO));
        dealer20_3.addCard(SPADE_EIGHT);
        dealer22_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_THREE));
        dealer22_3.addCard(SPADE_NINE);
        player20_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_TWO));
        player20_3.addCard(SPADE_EIGHT);
        dealer21_3 = HoldCards.init(List.of(SPADE_TEN, SPADE_TWO));
        dealer21_3.addCard(SPADE_NINE);
        dealer21_2 = HoldCards.init(List.of(SPADE_TEN, SPADE_ACE));
    }

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 높으면 승리를 반환한다.")
    void playerIsWin() {
        PlayerOutcome match = PlayerOutcome.match(player21_3, dealer20_3);
        assertThat(match).isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 낮으면 패배를 반환한다.")
    void playerIsLose() {
        PlayerOutcome match = PlayerOutcome.match(player20_3, dealer21_3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 딜러의 합이 같으면 무승부를 반환한다.")
    void playerIsDraw() {
        PlayerOutcome match = PlayerOutcome.match(player21_3, dealer21_3);
        assertThat(match).isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("플레이어의 합이 21이 넘는 경우 패배를 반환한다.")
    void playerIsLoseByOver21() {
        PlayerOutcome match = PlayerOutcome.match(player22_3, dealer21_3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러의 합이 21이 넘는 경우 승리를 반환한다.")
    void playerIsWinByDealerOver21() {
        PlayerOutcome match = PlayerOutcome.match(player21_3, dealer22_3);
        assertThat(match).isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 21이 넘는 경우 패배를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        PlayerOutcome match = PlayerOutcome.match(player22_3, dealer22_3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 숫자가 작을 경우 블랙잭 승을 반환한다.")
    void playerBlackJackWinTest() {
        PlayerOutcome match = PlayerOutcome.match(player21_2, dealer20_3);
        assertThat(match).isEqualTo(PlayerOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 3장 21인 경우 블랙잭 승을 반환한다.")
    void playerBlackJackWinDealer21Test() {
        PlayerOutcome match = PlayerOutcome.match(player21_2, dealer21_3);
        assertThat(match).isEqualTo(PlayerOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러도 블랙잭인 경우 무승부를 반환한다.")
    void playerBlackJackDealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(player21_2, dealer21_2);
        assertThat(match).isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 3장 21인 경우 무승부를 반환한다.")
    void player21DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(player21_3, dealer21_2);
        assertThat(match).isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 20인 경우 플레이어 패를 반환한다.")
    void player20DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(player20_3, dealer21_2);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 22인 경우 플레이어 패를 반환한다.")
    void player22DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(player22_3, dealer21_2);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }
}
