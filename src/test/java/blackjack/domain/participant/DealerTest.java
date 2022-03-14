package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        dealer = Dealer.of(hand);
    }

    @DisplayName("Dealer 인스턴스가 생성된다.")
    @Test
    void of() {
        assertThat(dealer).isNotNull();
    }

    @DisplayName("Card 를 전달받아 Hand 에 추가할 수 있다.")
    @Test
    void receiveCard() {
        dealer.receiveCard(CLOVER6);

        Set<Card> actual = dealer.getHand().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("이미 3장의 카드를 지니고 있는 경우, 4번째 카드를 추가하려고 하면 예외가 발생한다.")
    @Test
    void receiveCard_throwsExceptionIfAlreadyThreeCards() {
        dealer.receiveCard(CLOVER6);

        assertThatThrownBy(() -> dealer.receiveCard(CLOVER7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러는 최대 3개의 카드만 지닐 수 있습니다.");
    }

    @DisplayName("Score 가 16을 넘지 않으면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan16() {
        dealer.receiveCard(CLOVER6);
        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 16이면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOn16() {
        dealer.receiveCard(CLOVER7);

        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 17 이상이면 false 를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan16() {
        dealer.receiveCard(CLOVER8);

        boolean actual = dealer.canReceive();

        assertThat(actual).isFalse();
    }

    // TODO: Nested 사용하여 정리

    @DisplayName("compareWith 메소드는 딜러 자신보다 패가 나쁜 Participant 를 전달받으면 ResultType.WIN 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeWin() {
        // given
        Player winPlayer = Player.of("hudi", Hand.of(CLOVER2, CLOVER3));

        // when
        ResultType actual = dealer.compareWith(winPlayer);
        ResultType expected = ResultType.WIN;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("compareWith 메소드는 딜러 자신보다 패가 좋은 Participant 를 전달받으면 ResultType.LOSE 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeLose() {
        // given
        Player winPlayer = Player.of("hudi", Hand.of(CLOVER5, CLOVER6));

        // when
        ResultType actual = dealer.compareWith(winPlayer);
        ResultType expected = ResultType.LOSE;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("compareWith 메소드는 딜러 자신과 대등한 패를 가진 Participant 를 전달받으면 ResultType.DRAW 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeDraw() {
        // given
        Player winPlayer = Player.of("hudi", Hand.of(CLOVER3, CLOVER6));

        // when
        ResultType actual = dealer.compareWith(winPlayer);
        ResultType expected = ResultType.DRAW;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
