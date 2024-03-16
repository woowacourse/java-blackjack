package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.SPADE;
import static blackjack.model.participant.Player.CAN_NOT_BLANK_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성: ")
    class PlayerCreation {
        @Test
        @DisplayName("참여할 사람은 이름을 가진다.")
        void haveNameWhenCreatePlayer() {
            String name = "리브";
            Player player = new Player(name);
            assertThat(player.getName()).isEqualTo(name);
        }

        @ParameterizedTest(name = "[{index}] 플레이어 이름 : `{0}`")
        @NullAndEmptySource
        @ValueSource(strings = {" ", "  "})
        @DisplayName("플레이어 이름이 공백이거나 없으면 예외를 던진다.")
        void throwExceptionWhenCreatePlayerByBlank(String name) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Player(name))
                    .withMessage(CAN_NOT_BLANK_NAME);
        }

        @Test
        @DisplayName("이름 입력시 앞 뒤에 공백을 입력하면 제거한다.")
        void trimName() {
            String name = "  리브  ";
            Player player = new Player(name);
            assertThat(player.getName()).isEqualTo("리브");
        }
    }

    @Nested
    @DisplayName("플레이어 카드 받기: ")
    class PlayerReceiveCard {
        private Player player;

        @BeforeEach
        void init() {
            player = new Player("몰리");
        }

        @Test
        @DisplayName("초기 카드 2장을 받는다.")
        void receiveInitialCards() {
            List<Card> cards = List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
            player.receiveInitialCards(cards);

            assertThat(player.openCards()).containsExactly(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
        }

        @Test
        @DisplayName("카드를 추가로 받을 수 있다.")
        void canReceiveExtraCard() {
            List<Card> cards = List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE));
            player.receiveInitialCards(cards);
            player.draw(new Card(CLOVER, TWO));

            assertThat(player.openCards())
                    .containsExactly(new Card(CLOVER, ACE), new Card(CLOVER, THREE), new Card(CLOVER, TWO));
        }

        @Test
        @DisplayName("소지한 카드의 총 합이 21을 초과하지 않으면 카드를 더 받을 권리가 있다.")
        void playerCanReceiveCardWhenTotalScoreLessThan21() {
            player.receiveInitialCards(List.of(new Card(DIA, TEN), new Card(CLOVER, TEN)));

            assertThat(player.canHit()).isTrue();
        }

        @Test
        @DisplayName("소지한 카드의 총 합이 21을 초과하면 더 받을 권리가 없다.")
        void playerCanNotReceiveCardWhenTotalScoreOver21() {
            player.receiveInitialCards(List.of(new Card(DIA, TEN), new Card(CLOVER, TEN)));
            player.draw(new Card(SPADE, TWO));

            assertThat(player.canHit()).isFalse();
        }
    }
}
