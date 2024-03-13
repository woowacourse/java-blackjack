package blackjack.domain.game.result;

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

class PlayerWinTest {

    private static final Name DEFAULT_NAME = new Name("name");
    private static final GameResult playerWin = PlayerWin.getInstance();

    @DisplayName("플레이어_승리 조건 테스트")
    @Nested
    class satisfyTest {

        @DisplayName("플레이어가 블랙잭이라면 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void playerBustTest(List<Card> dealerCards) {
            Player player = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);
            Dealer dealer = new Dealer(dealerCards);

            assertThat(playerWin.isSatisfy(dealer, player)).isFalse();
        }

        @DisplayName("딜러가 블랙잭이라면 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void dealerBlackjackTest(List<Card> playerCards) {

            Player player = new Player(playerCards, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.BLACKJACK);

            assertThat(playerWin.isSatisfy(dealer, player)).isFalse();
        }

        @DisplayName("둘 다 버스트, 블랙잭이 없을 때 플레이어가 점수가 높다면 조건을 만족한다.")
        @Test
        void highScoreTest() {

            Player player = new Player(CardsFixture.CARDS_SCORE_17, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

            assertThat(playerWin.isSatisfy(dealer, player)).isTrue();
        }

        @DisplayName("플레이어가 버스트 된다면 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void dealerBustTest(List<Card> dealerCards) {

            Player player = new Player(CardsFixture.BUSTED, DEFAULT_NAME);
            Dealer dealer = new Dealer(dealerCards);

            assertThat(playerWin.isSatisfy(dealer, player)).isFalse();
        }

        static Stream<List<Card>> allCardCase() {
            return Stream.of(
                    CardsFixture.BLACKJACK,
                    CardsFixture.BUSTED,
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_4
            );
        }
    }
}
