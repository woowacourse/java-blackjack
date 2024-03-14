package blackjack.domain.game.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.fixture.CardsFixture;
import blackjack.domain.game.Referee;
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

class RefereeTest {
    private static final Name DEFALUT_NAME = new Name("name");

    @DisplayName("게임 결과 조회 테스트")
    @Nested
    class GameResultTest {

        @DisplayName("플레이어와 딜러가 모두 버스트 되지 않고 결과가 같은 경우, PUSH 로 취급된다.")
        @ParameterizedTest
        @MethodSource("notBustedCards")
        void pushTest(List<Card> cards) {
            Player player = new Player(cards, DEFALUT_NAME);
            Dealer dealer = new Dealer(cards);

            GameResult result = Referee.getGameResult(dealer, player);
            assertThat(result).isEqualTo(Push.getInstance());
        }

        @DisplayName("플레이어만 버스트가 된 경우, 플레이어가 진다.")
        @ParameterizedTest
        @MethodSource("notBustedCards")
        void playerBustTest(List<Card> notBustedCards) {
            Player player = new Player(CardsFixture.BUSTED, DEFALUT_NAME);
            Dealer dealer = new Dealer(notBustedCards);

            GameResult result = Referee.getGameResult(dealer, player);
            assertThat(result).isEqualTo(PlayerLose.getInstance());
        }

        static Stream<List<Card>> notBustedCards() {
            return Stream.of(
                    CardsFixture.BLACKJACK,
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_17
            );
        }

        @DisplayName("플레이어만 블랙잭인 경우 플레이어_블랙잭으로 취급된다.")
        @ParameterizedTest
        @MethodSource("notBlackjackCards")
        void playerBlackjackTest(List<Card> notBlackjackCard) {
            Player blackjackPlayer = new Player(CardsFixture.BLACKJACK, DEFALUT_NAME);
            Dealer dealer = new Dealer(notBlackjackCard);

            GameResult result = Referee.getGameResult(dealer, blackjackPlayer);
            assertThat(result).isEqualTo(PlayerBlackjack.getInstance());
        }

        static Stream<List<Card>> notBlackjackCards() {
            return Stream.of(
                    CardsFixture.BUSTED,
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_4
            );
        }

        @DisplayName("플레이어가 블랙잭이 아니고, 딜러만 버스트일 경우 플레이어가 이긴다.")
        @ParameterizedTest
        @MethodSource("notBlackjackAndBustedCards")
        void dealerBustTest(List<Card> notBlackjackAndBustedCard) {
            Player notBlackjackAndBustedPlayer = new Player(notBlackjackAndBustedCard, DEFALUT_NAME);
            Dealer bustedDealer = new Dealer(CardsFixture.BUSTED);

            GameResult result = Referee.getGameResult(bustedDealer, notBlackjackAndBustedPlayer);
            assertThat(result).isEqualTo(PlayerWin.getInstance());
        }

        static Stream<List<Card>> notBlackjackAndBustedCards() {
            return Stream.of(
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_4
            );
        }

        @DisplayName("모두 블랙잭, 버스트, 동점이 아닌 경우, 점수가 높은 사람이 이긴다.")
        @Nested
        class winTest {

            @DisplayName("플레이어가 이긴다.")
            @Test
            void playerWinTest() {
                Player player = new Player(CardsFixture.CARDS_SCORE_17, DEFALUT_NAME);
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

                GameResult result = Referee.getGameResult(dealer, player);
                assertThat(result).isEqualTo(PlayerWin.getInstance());
            }

            @DisplayName("딜러가 이긴다.")
            @Test
            void dealerWinTest() {
                Player player = new Player(CardsFixture.CARDS_SCORE_16, DEFALUT_NAME);
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

                GameResult result = Referee.getGameResult(dealer, player);
                assertThat(result).isEqualTo(PlayerLose.getInstance());
            }
        }
    }
}
