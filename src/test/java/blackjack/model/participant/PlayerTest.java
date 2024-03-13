package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.QUEEN;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import blackjack.model.state.BlackJack;
import blackjack.model.state.Bust;
import blackjack.model.state.Hit;
import blackjack.model.state.Stand;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerTest {

    @Test
    @DisplayName("참여할 사람은 이름을 가진다.")
    void createPlayer() {
        String name = "리브";
        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    @ParameterizedTest(name = "이름 입력 시 blank가 들어온 경우 예외를 발생한다.")
    @NullAndEmptySource
    void createPlayerByBlank(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(name))
                .withMessage("이름에는 공백을 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("이름 입력시 앞 뒤에 공백을 입력하면 제거한다.")
    void trimName() {
        String name = "  리브  ";
        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo("리브");
    }

    @Nested
    @DisplayName("플레이어 카드 받기")
    class ReceiveCard {
        private Player player;

        @BeforeEach
        void init() {
            player = new Player("몰리");
        }

        @Test
        @DisplayName("플레이어는 초기 카드 2장을 받는다.")
        void receiveInitialCards() {
            List<Card> cards = List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
            player.receiveInitialCards(cards);

            assertThat(player.openCards()).containsExactly(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
        }

        @Test
        @DisplayName("참여할 사람은 카드를 추가로 받을 수 있다.")
        void createPlayerWithCards() {
            List<Card> cards = List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
            player.receiveInitialCards(cards);
            player.draw(new Card(CLOVER, TWO));

            assertThat(player.openCards())
                    .containsExactly(new Card(CLOVER, ACE), new Card(CLOVER, THREE), new Card(CLOVER, TWO));
        }
    }

    @Test
    @DisplayName("플레이어는 소지한 카드의 총 합이 21을 초과하지 않으면 더 받을 수 있다.")
    void checkNotBust() {
        Player player = new Player("몰리");
        player.receiveInitialCards(List.of(new Card(DIA, TEN), new Card(CLOVER, TEN)));

        assertThat(player.canHit()).isTrue();
    }

    @Nested
    @DisplayName("플레이어의 현재 상태")
    class StateOfPlayer {
        private Player player;

        @BeforeEach
        void init() {
            player = new Player("리브");
        }

        @Test
        @DisplayName("카드의 점수가 21 미만이면 Hit 상태이다.")
        void hit() {
            player.receiveInitialCards(List.of(new Card(SPADE, TWO), new Card(DIA, THREE)));

            assertThat(player.getState()).isInstanceOf(Hit.class);
        }

        @Test
        @DisplayName("2장의 카드로 21점이면 BlackJack 상태이다.")
        void blackJack() {
            player.receiveInitialCards(List.of(new Card(SPADE, ACE), new Card(DIA, QUEEN)));

            assertThat(player.getState()).isInstanceOf(BlackJack.class);
        }

        @Test
        @DisplayName("카드의 점수가 21을 초과하면 Bust 상태이다.")
        void bust() {
            player.receiveInitialCards(List.of(new Card(SPADE, QUEEN), new Card(DIA, QUEEN)));
            player.draw(new Card(CLOVER, TWO));

            assertThat(player.getState()).isInstanceOf(Bust.class);
        }

        @Test
        @DisplayName("플레이어가 더 이상 카드를 받지 않는 것을 선택하면 Stand 상태가 된다.")
        void stand() {
            player.receiveInitialCards(List.of(new Card(SPADE, QUEEN), new Card(DIA, QUEEN)));
            player.stand();

            assertThat(player.getState()).isInstanceOf(Stand.class);
        }
    }
}
