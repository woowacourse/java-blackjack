package domain.game;

import domain.deck.TestDeckForThreeParticipant;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class BetTest {

    @DisplayName("100,000,000이하의 배팅 금액은 허용된다.")
    @ParameterizedTest
    @ValueSource(ints = {99_999_999, 100_000_000})
    void validBet(int value) {
        assertDoesNotThrow(() -> Bet.of(value));
    }

    @DisplayName("100,000,000초과의 배팅 금액은 예외가 발생한다.")
    @Test
    void invalidBet() {
        assertThatThrownBy(() -> Bet.of(100_000_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100,000,000초과의 베팅은 할 수 없습니다.");
    }

    @DisplayName("블랙잭이면 배팅한 금액의 1.5배의 수익률을 가진다.")
    @Test
    void bonus() {
        final Bet bet = Bet.of(10_000);
        bet.applyBlackJack();
        assertThat(bet.getProfit())
                .isEqualTo(15_000);
    }

    @DisplayName("버스트가 나면 배팅한 금액을 모두 잃고 수익률을 계산한다.")
    @Test
    void bust() {
        final Bet bet = Bet.of(10_000);
        bet.applyBust();
        assertThat(bet.getProfit())
                .isEqualTo(-10_000);
    }

    @Nested
    class BetDealerPlayersTest {
        private BlackJack blackJack;
        private Player player1;
        private Player player2;
        private Dealer dealer;

        @BeforeEach
        void init() {
            final List<Name> names = List.of(Name.of("a"), Name.of("b"));
            final List<Integer> bets = List.of(1000, 2000);
            blackJack = BlackJack.getInstance(names, bets, new TestDeckForThreeParticipant());

            final Players players = blackJack.getPlayers();
            final List<Player> playerList = players.getPlayers();
            player1 = playerList.get(0);
            player2 = playerList.get(1);
            dealer = blackJack.getDealer();
        }

        @DisplayName("플레이어들은 각각 배팅 금액을 가지고 있다.")
        @Test
        void playerBetTest() {
            assertThat(player1.getBet()).isEqualTo(Bet.of(1000));
            assertThat(player2.getBet()).isEqualTo(Bet.of(2000));
        }

        @DisplayName("플레이어들과 딜러의 초기 점수를 확인한다.")
        @Test
        void initialGamePointTest() {
            gamePointCheck(player1, 21);
            gamePointCheck(player2, 7);
            gamePointCheck(dealer, 15);
        }

        @DisplayName("플레이어들의 초기 수익률을 구한다.")
        @Test
        void betProfitTest() {
            betProfitCheck(player1, 1500);
            betProfitCheck(player2, 2000);
        }

        @Test
        void name() {

        }

        private void gamePointCheck(final Participant participant, final int gamePoint) {
            assertThat(participant)
                    .extracting("cards")
                    .extracting("gamePoint")
                    .extracting("gamePoint")
                    .isEqualTo(gamePoint);
        }

        private void betProfitCheck(final Player player, final int profit) {
            assertThat(player)
                    .extracting("bet")
                    .extracting("Profit")
                    .isEqualTo(profit);
        }
    }

}
