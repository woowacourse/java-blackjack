package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.fixture.CardsFixture;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GameResultTest {

    private final Name DEFAULT_NAME = new Name("name");

    @DisplayName("플레이어가 블랙잭인 경우")
    @Nested
    class BlackjackPlayerTest {
        Player blackjackPlayer = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);

        @DisplayName("딜러도 블랙잭이라면 PUSH 이다.")
        @Test
        void blackjackDealerTest() {
            Dealer blackjackDealer = new Dealer(CardsFixture.BLACKJACK);
            GameResult gameResult = GameResult.of(blackjackDealer, blackjackPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PUSH);
        }

        @DisplayName("딜러가 블랙잭이 아니라면 플레이어의 블랙잭 승이다.")
        @ParameterizedTest
        @MethodSource("nonBlackjackFixture")
        void nonBlackjackDealerTest(List<Card> dealerCard) {
            Dealer nonBlackjackDealer = new Dealer(dealerCard);
            GameResult gameResult = GameResult.of(nonBlackjackDealer, blackjackPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_BLACKJACK);
        }

        static Stream<List<Card>> nonBlackjackFixture() {
            return Stream.of(
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_4,
                    CardsFixture.BUSTED

            );
        }
    }

    @DisplayName("플레이어가 버스트인 경우")
    @Nested
    class BustedPlayerTest {
        Player BustedPlayer = new Player(CardsFixture.BUSTED, DEFAULT_NAME);

        @DisplayName("딜러가 블랙잭이라면 플레이어 패배이다.")
        @Test
        void blackjackDealerTest() {
            Dealer blackjackDealer = new Dealer(CardsFixture.BLACKJACK);
            GameResult gameResult = GameResult.of(blackjackDealer, BustedPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_LOSE);
        }

        @DisplayName("딜러가 버스트라면 플레이어 패배이다.")
        @Test
        void bustedDealerTest() {
            Dealer blackjackDealer = new Dealer(CardsFixture.BUSTED);
            GameResult gameResult = GameResult.of(blackjackDealer, BustedPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_LOSE);
        }

        @DisplayName("딜러가 일반 조합이라면 플레이어 패배이다.")
        @Test
        void normalDealerTest() {
            Dealer normalDealer = new Dealer(CardsFixture.CARDS_SCORE_4);
            GameResult gameResult = GameResult.of(normalDealer, BustedPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_LOSE);
        }
    }

    @DisplayName("플레이어가 일반 카드일 경우")
    @Nested
    class normalPlayerTest {
        Player normalPlayer = new Player(CardsFixture.CARDS_SCORE_17, DEFAULT_NAME);

        @DisplayName("딜러만 블랙잭이라면 플레이어 패배이다.")
        @Test
        void blackjackDealerTest() {
            Player nonBlackjactPlayer = new Player(CardsFixture.CARDS_SCORE_21, DEFAULT_NAME);
            Dealer blackjackDealer = new Dealer(CardsFixture.BLACKJACK);
            GameResult gameResult = GameResult.of(blackjackDealer, nonBlackjactPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_LOSE);
        }

        @DisplayName("딜러가 버스트라면 플레이어 승리이다.")
        @Test
        void bustedDealerTest() {
            Dealer blackjackDealer = new Dealer(CardsFixture.BUSTED);
            GameResult gameResult = GameResult.of(blackjackDealer, normalPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_WIN);
        }

        @DisplayName("딜러가 노멀 핸드이면서 플레이어보다 점수가 높으면 플레이어 패배이다.")
        @Test
        void higherScoreDealerTest() {
            Dealer commonDealer = new Dealer(CardsFixture.CARDS_SCORE_18);
            GameResult gameResult = GameResult.of(commonDealer, normalPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_LOSE);
        }

        @DisplayName("딜러가 노멀 핸드이면서 플레이어와 점수가 같으면 무승부(PUSH) 이다.")
        @Test
        void drawScoreDealerTest() {
            Dealer commonDealer = new Dealer(CardsFixture.CARDS_SCORE_17);
            GameResult gameResult = GameResult.of(commonDealer, normalPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PUSH);
        }

        @DisplayName("딜러가 노멀 핸드이면서 플레이어보다 점수가 낮으면 플레이어 승리이다.")
        @Test
        void lowerScoreDealerTest() {
            Dealer commonDealer = new Dealer(CardsFixture.CARDS_SCORE_16);
            GameResult gameResult = GameResult.of(commonDealer, normalPlayer);
            assertThat(gameResult).isEqualTo(GameResult.PLAYER_WIN);
        }
    }
}
