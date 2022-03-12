package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;
    private CardBundle cardBundle;

    @BeforeEach
    void setUp() {
        cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        player = Player.of("hudi", cardBundle);
    }

    @DisplayName("플레이어의 이름을 빈 문자열로 생성하려는 경우 예외가 발생한다.")
    @Test
    void constructor_throwExceptionOnBlankName() {
        assertThatThrownBy(() -> Player.of("", cardBundle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 이름을 지녀야 합니다.");
    }

    @DisplayName("플레이어의 이름을 '딜러'로 생성하려는 경우 예외가 발생한다.")
    @Test
    void constructor_throwExceptionOnDealerName() {
        assertThatThrownBy(() -> Player.of("딜러", cardBundle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 딜러가 될 수 없습니다.");
    }

    @DisplayName("카드를 전달받아 cardBundle에 추가할 수 있다.")
    @Test
    void receiveCard() {
        player.receiveCard(CLOVER6);

        Set<Card> actual = player.getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("canReceive 메서드 테스트")
    @Nested
    class CanReceiveTest {

        @DisplayName("점수가 21을 넘지 않으면 true를 반환한다.")
        @Test
        void canReceive_returnTrueOnLessThan21() {
            boolean actual = player.canReceive();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 21이면 true를 반환한다.")
        @Test
        void canReceive_returnTrueOn21() {
            player.receiveCard(CLOVER2);
            player.receiveCard(CLOVER10);

            boolean actual = player.canReceive();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 21을 초과하면 false를 반환한다.")
        @Test
        void canReceive_returnFalseOnGreaterThan21() {
            player.receiveCard(CLOVER10);
            player.receiveCard(CLOVER_KING);

            boolean actual = player.canReceive();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("isBust 메서드 테스트")
    @Nested
    class IsBustTest {

        @DisplayName("점수가 21을 넘지 않으면 false를 반환한다.")
        @Test
        void isBust_returnFalseOn21OrLess() {
            boolean actual = player.isBust();

            assertThat(actual).isFalse();
        }

        @DisplayName("점수가 21을 넘으면 true를 반환한다.")
        @Test
        void isBust_returnTrueOnOver21() {
            player.receiveCard(CLOVER10);
            player.receiveCard(CLOVER_KING);

            boolean actual = player.isBust();

            assertThat(actual).isTrue();
        }
    }
}
