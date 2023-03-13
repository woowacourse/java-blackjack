package domain.game;

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
            assertThat(player1.getBet()).isEqualTo(1000);
            assertThat(player2.getBet()).isEqualTo(2000);
        }

        @DisplayName("플레이어들과 딜러의 초기 점수를 확인한다.")
        @Test
        void initialGamePointTest() {
            gamePointCheck(player1, 21);
            gamePointCheck(player2, 7);
            gamePointCheck(dealer, 15);
        }

        private void gamePointCheck(final Participant participant, final int gamePoint) {
            assertThat(participant)
                    .extracting("cards")
                    .extracting("gamePoint")
                    .extracting("gamePoint")
                    .isEqualTo(gamePoint);
        }
    }
}
