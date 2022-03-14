package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.List;
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

        List<Card> actual = player.getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("canDraw 메서드 테스트")
    @Nested
    class CanDrawTest {

        @DisplayName("점수가 21을 넘지 않으면 true를 반환한다.")
        @Test
        void canDraw_returnTrueOnLessThan21() {
            boolean actual = player.canDraw();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 최고 점수인 21이면 false를 반환한다.")
        @Test
        void canDraw_returnFalseOn21() {
            player.receiveCard(CLOVER2);
            player.receiveCard(CLOVER10);

            boolean actual = player.canDraw();

            assertThat(actual).isFalse();
        }

        @DisplayName("점수가 21을 초과하면 false를 반환한다.")
        @Test
        void canDraw_returnFalseOnGreaterThan21() {
            player.receiveCard(CLOVER10);
            player.receiveCard(CLOVER_KING);

            boolean actual = player.canDraw();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("Player 인스턴스에는 CardBundle의 isBust 메서드가 구현되어있다.")
    @Test
    void isBust_implementationTest() {
        player.receiveCard(CLOVER10);
        player.receiveCard(CLOVER_KING);

        boolean actual = player.isBust();

        assertThat(actual).isTrue();
    }

    @DisplayName("Player 인스턴스에는 Participant의 isBlackjack 메서드가 구현되어있다.")
    @Test
    void isBlackjack_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER10, CLOVER_ACE);
        Player player = Player.of("hudi", cardBundle);

        boolean actual = player.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어가 명시적으로 stay 메서드를 호출한 경우, 버스트이나 블랙잭이 아니어도, hit 메서드를 호출했을 때 예외가 발생하게 된다.")
    @Test
    void stay() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER7, CLOVER10);
        Player player = Player.of("jeong", cardBundle);
        assertThat(player.canDraw()).isTrue();

        player.stay();

        assertThat(player.canDraw()).isFalse();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> player.receiveCard(CLOVER3))
                .withMessage("이미 카드 패가 확정된 참여자입니다.");
        assertThat(player.isBlackjack()).isFalse();
        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("플레이어의 getInitialOpenCards 메서드는 초기에 받은 두 장의 카드가 담긴 컬렉션을 반환한다.")
    @Test
    void getInitialOpenCards() {
        List<Card> actual = player.getInitialOpenCards();

        assertThat(actual).containsExactly(CLOVER4, CLOVER5);
        assertThat(actual.size()).isEqualTo(2);
    }
}
