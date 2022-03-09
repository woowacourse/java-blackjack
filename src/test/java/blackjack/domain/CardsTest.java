package blackjack.domain;

import static blackjack.domain.CardNumber.A;
import static blackjack.domain.CardNumber.FIVE;
import static blackjack.domain.CardNumber.JACK;
import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardNumber.TEN;
import static blackjack.domain.CardNumber.THREE;
import static blackjack.domain.CardNumber.TWO;
import static blackjack.domain.CardPattern.HEART;
import static blackjack.domain.CardPattern.SPADE;
import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드의 합의 반환한다.")
    void calculateScore() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE), Card.of(SPADE, TEN)));
        assertThat(cards.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("생성 시 카드에는 null이 들어올 경우 예외를 발생해야 한다.")
    void createNullException() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[Error] 카드에는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("21이 넘는데 카드를 더할 경우 예외가 발생해야 한다.")
    void addExceptionBySumIsLargerThanBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, KING), Card.of(SPADE, TWO)));
        assertThatThrownBy(() -> cards.addCard(Card.of(SPADE, FIVE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[Error] 21이 넘을때는 카드를 더 추가할 수 없습니다.");
    }

    @Test
    @DisplayName("둘 다 블랙잭인 경우 무승부를 반환한다.")
    void fightResultBothBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A)));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, A)));
        assertThat(cards.fightResult(compareCards)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("자신만 블랙잭인 경우 우승를 반환한다.")
    void fightResultSelfBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A)));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK), Card.of(HEART, A)));
        assertThat(cards.fightResult(compareCards)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("상대방만 블랙잭인 경우 패배를 반환한다.")
    void fightResultNotSelfBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK), Card.of(HEART, A)));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A)));
        assertThat(cards.fightResult(compareCards)).isEqualTo(LOSE);
    }
}