package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        dealer = Dealer.of(cardBundle);
    }

    @DisplayName("카드를 전달받아 cardBundle에 추가할 수 있다.")
    @Test
    void receiveCard() {
        dealer.receiveCard(CLOVER6);

        List<Card> actual = dealer.getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("canDraw 메서드 테스트")
    @Nested
    class CanDrawTest {

        @DisplayName("점수가 16을 넘지 않으면 true를 반환한다.")
        @Test
        void canDraw_returnTrueOnLessThan16() {
            dealer.receiveCard(CLOVER6);
            boolean actual = dealer.canDraw();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 16이면 true를 반환한다.")
        @Test
        void canDraw_returnTrueOn16() {
            dealer.receiveCard(CLOVER7);

            boolean actual = dealer.canDraw();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 17 이상이면 false를 반환한다.")
        @Test
        void canDraw_returnFalseOnGreaterThan16() {
            dealer.receiveCard(CLOVER8);

            boolean actual = dealer.canDraw();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("Dealer 인스턴스에는 CardBundle의 isBust 메서드가 구현되어있다.")
    @Test
    void isBust_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER6, CLOVER10);
        dealer = Dealer.of(cardBundle);
        dealer.receiveCard(CLOVER_KING);

        boolean actual = dealer.isBust();

        assertThat(actual).isTrue();
    }

    @DisplayName("Dealer 인스턴스에는 Participant의 isBlackjack 메서드가 구현되어있다.")
    @Test
    void isBlackjack_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER10, CLOVER_ACE);
        Dealer dealer = Dealer.of(cardBundle);

        boolean actual = dealer.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("딜러의 패가 17이상 21이하인 경우 버스트도, 블랙잭도 아니지만, hit 메서드를 호출하는 경우 예외가 발생한다.")
    @Test
    void dealerStayTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER7, CLOVER10);
        Dealer dealer = Dealer.of(cardBundle);

        assertThat(dealer.canDraw()).isFalse();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dealer.receiveCard(CLOVER3))
                .withMessage("이미 카드 패가 확정된 참여자입니다.");

        assertThat(dealer.isBlackjack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
    }

    @DisplayName("딜러의 getInitialOpenCards 메서드는 초기에 받은 카드 중 한 장이 담긴 컬렉션을 반환한다.")
    @Test
    void getInitialOpenCards() {
        List<Card> actual = dealer.getInitialOpenCards();

        assertThat(actual).containsExactly(CLOVER4);
        assertThat(actual.size()).isEqualTo(1);
    }
}