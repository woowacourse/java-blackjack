package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static blackjack.fixture.CardRepository.DIAMOND_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.fixture.CardBundleGenerator;
import java.util.Set;
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

        Set<Card> actual = dealer.getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("점수가 16을 넘지 않으면 true를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan16() {
        dealer.receiveCard(CLOVER6);
        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("canReceive 메서드 테스트")
    @Nested
    class CanReceiveTest {

        @DisplayName("점수가 16이면 true를 반환한다.")
        @Test
        void canReceive_returnTrueOn16() {
            dealer.receiveCard(CLOVER7);

            boolean actual = dealer.canReceive();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 17 이상이면 false를 반환한다.")
        @Test
        void canReceive_returnFalseOnGreaterThan16() {
            dealer.receiveCard(CLOVER8);

            boolean actual = dealer.canReceive();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("isBust 메서드 테스트")
    @Nested
    class IsBustTest {

        @DisplayName("점수가 21을 넘지 않으면 false를 반환한다.")
        @Test
        void isBust_returnFalseOn21OrLess() {
            boolean actual = dealer.isBust();

            assertThat(actual).isFalse();
        }

        @DisplayName("점수가 21을 넘으면 true를 반환한다.")
        @Test
        void isBust_returnTrueOnOver21() {
            dealer.receiveCard(CLOVER10);
            dealer.receiveCard(CLOVER_KING);

            boolean actual = dealer.isBust();

            assertThat(actual).isTrue();
        }
    }

    @DisplayName("isBlackjack 메서드 테스트")
    @Nested
    class IsBlackjackTest {

        @DisplayName("점수가 21이면 true를 반환한다.")
        @Test
        void isBlackjack_returnTruOn21() {
            dealer.receiveCard(CLOVER_ACE);
            dealer.receiveCard(DIAMOND_ACE);

            boolean actual = dealer.isBlackjack();

            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 21이 아니면 false를 반환한다.")
        @Test
        void isBlackjack_returnTrueIfNot21() {
            dealer.receiveCard(CLOVER_ACE);

            boolean actual = dealer.isBust();

            assertThat(actual).isFalse();
        }
    }
}