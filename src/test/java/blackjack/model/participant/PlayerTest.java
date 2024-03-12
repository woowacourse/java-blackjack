package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
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
        Player player;

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
}
