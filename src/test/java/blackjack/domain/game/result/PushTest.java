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

class PushTest {

    private static final Name DEFAULT_NAME = new Name("name");
    private static final GameResult push = Push.getInstance();

    @DisplayName("무승부 조건 테스트")
    @Nested
    class satisfyTest {

        @DisplayName("플레이어와 딜러 모두 블랙잭이라면 조건을 만족한다.")
        @Test
        void bothBlackjackTest() {
            Player player = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.BLACKJACK);

            assertThat(push.isSatisfy(dealer, player)).isTrue();
        }

        @DisplayName("모두 블랙잭이 아니고 점수가 같으면 조건을 만족한다.")
        @Test
        void sameScoreTest() {

            Player player = new Player(CardsFixture.CARDS_SCORE_17, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

            assertThat(push.isSatisfy(dealer, player)).isTrue();
        }

        @DisplayName("플레이어가 버스트라면 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void playerBustTest(List<Card> dealerCards) {

            Player player = new Player(CardsFixture.BUSTED, DEFAULT_NAME);
            Dealer dealer = new Dealer(dealerCards);

            assertThat(push.isSatisfy(dealer, player)).isFalse();
        }

        @DisplayName("딜러가 버스트라면 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void dealerBustTest(List<Card> playerCards) {

            Player player = new Player(playerCards, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.BUSTED);

            assertThat(push.isSatisfy(dealer, player)).isFalse();
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
