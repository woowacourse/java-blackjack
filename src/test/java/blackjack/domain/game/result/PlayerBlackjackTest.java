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

class PlayerBlackjackTest {

    private static final Name DEFAULT_NAME = new Name("name");
    private static final GameResult playerBlackjack = PlayerBlackjack.getInstance();

    @DisplayName("플레이어_블랙잭 상태 확인 테스트")
    @Nested
    class satisfyTest {

        @DisplayName("플레이어가 블랙잭이고 딜러가 버스트, 블랙잭이 아니라면 플레이어_블랙잭 조건을 만족한다.")
        @Test
        void playerBlackjackTest() {
            Player player = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

            assertThat(playerBlackjack.isSatisfy(dealer, player)).isTrue();
        }

        @DisplayName("딜러가 블랙잭이라면 플레이어_블랙잭 조건을 만족하지 않는다.")
        @Test
        void dealerBlackjackTest() {
            Player player = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.BLACKJACK);

            assertThat(playerBlackjack.isSatisfy(dealer, player)).isFalse();
        }

        @DisplayName("플레이어가 블랙잭이 아니라면 플레이어_블랙잭 조건을 만족하지 않는다.")
        @ParameterizedTest
        @MethodSource("allCardCase")
        void playerNotBlackjackTest(List<Card> dealerCard) {
            Player player = new Player(CardsFixture.CARDS_SCORE_21, DEFAULT_NAME);
            Dealer dealer = new Dealer(dealerCard);

            assertThat(playerBlackjack.isSatisfy(dealer, player)).isFalse();
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
