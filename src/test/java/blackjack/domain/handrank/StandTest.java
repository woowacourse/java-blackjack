package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StandTest {

    private final HankRank STAND = new Stand(20);

    @DisplayName("딜러가 21점 이하 점수의 일반적인 경우")
    @Nested
    class MatchTest {
        @DisplayName("플레이어가 블랙잭인 경우, 플레이어가 블랙잭으로 승리한다.")
        @Test
        void whenOnlyPlayerBlackjack_playerBlackjack() {
            HankRank dealer = new Stand(21);
            HankRank player = new Blackjack();

            assertThat(dealer.competeWithPlayer(player)).isEqualTo(SingleMatchResult.PLAYER_BLACKJACK);
        }

        @DisplayName("플레이어가 버스트인 경우, 딜러가 승리한다.")
        @Test
        void whenOnlyPlayerBust_DealerWin() {
            HankRank dealer = STAND;
            HankRank player = new Bust(22);

            assertThat(dealer.competeWithPlayer(player)).isEqualTo(SingleMatchResult.DEALER_WIN);
        }

        @DisplayName("플레이어가 딜러보다 점수가 높을 경우, 플레이러가 승리한다.")
        @ParameterizedTest
        @CsvSource({"20, 21", "17, 18", "17, 21"})
        void whenPlayerScoreIsMoreThanDealerScore_PlayerWin(int dealerScore, int playerScore) {
            HankRank dealer = new Stand(dealerScore);
            HankRank player = new Stand(playerScore);

            assertThat(dealer.competeWithPlayer(player)).isEqualTo(SingleMatchResult.PLAYER_WIN);
        }

        @DisplayName("플레이어와 딜러의 점수가 같을 경우, 비긴다.")
        @ParameterizedTest
        @ValueSource(ints = {17, 18, 21})
        void whenPlayerScoreIsEqualToDealerScore_Draw(int sameScore) {
            HankRank dealer = new Stand(sameScore);
            HankRank player = new Stand(sameScore);

            assertThat(dealer.competeWithPlayer(player)).isEqualTo(SingleMatchResult.DRAW);
        }

        @DisplayName("플레이어가 딜러보다 점수가 높을 경우, 플레이러가 승리한다.")
        @ParameterizedTest
        @CsvSource({"21, 20", "17, 4", "17, 16"})
        void whenPlayerScoreIsLessThanDealerScore_Dealer(int dealerScore, int playerScore) {
            HankRank dealer = new Stand(dealerScore);
            HankRank player = new Stand(playerScore);

            assertThat(dealer.competeWithPlayer(player)).isEqualTo(SingleMatchResult.DEALER_WIN);
        }
    }


    @DisplayName("해당 핸드 랭크는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {

        assertThat(STAND.isBlackjack()).isFalse();
    }

    @DisplayName("해당 핸드 랭크는 버스트가 아니다.")
    @Test
    void isBustTest() {

        assertThat(STAND.isBust()).isFalse();
    }
}
