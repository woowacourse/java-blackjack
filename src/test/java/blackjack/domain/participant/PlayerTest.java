package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        player = Player.of("hudi", hand);
    }

    @DisplayName("Player 인스턴스가 생성된다.")
    @Test
    void of() {
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 의 이름이 비어있을 경우 예외가 발생한다.")
    @Test
    void of_withEmptyNameThrowsIllegalArgumentException() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        assertThatThrownBy(() -> Player.of("", hand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자 이상이어야합니다.");
    }

    @DisplayName("Card 를 전달받아 Hand 에 추가할 수 있다.")
    @Test
    void receiveCard() {
        player.receiveCard(CLOVER6);

        Set<Card> actual = player.getHand().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("Score 가 21을 넘지 않으면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan21() {
        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21이면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOn21() {
        player.receiveCard(CLOVER2);
        player.receiveCard(CLOVER10);

        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21을 초과하면 false 를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan21() {
        player.receiveCard(CLOVER10);
        player.receiveCard(CLOVER_KING);

        boolean actual = player.canReceive();

        assertThat(actual).isFalse();
    }

    // TODO: Nested 사용하여 정리

    @DisplayName("compareWith 메소드는 딜러 자신보다 패가 나쁜 Participant 를 전달받으면 ResultType.WIN 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeWin() {
        // given
        Player winPlayer = Player.of("hudi", Hand.of(CLOVER2, CLOVER3));

        // when
        ResultType actual = player.compareWith(winPlayer);
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
        ResultType actual = player.compareWith(winPlayer);
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
        ResultType actual = player.compareWith(winPlayer);
        ResultType expected = ResultType.DRAW;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
