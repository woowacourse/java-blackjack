package blackjack.domain.card;

import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;
import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드의 합을 계산할 수 있다.")
    void calculateScore() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE)));
        assertThat(cards.calculateScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("생성 시 카드에는 null이 들어올 경우 예외를 발생해야 한다.")
    void createNullException() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드에는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("생성 시 카드에는 2장의 카드가 들어오지 않을 경우 예외를 발생해야 한다.")
    void createExceptionBySize() {
        final List<Card> cards = Arrays.asList(Card.of(SPADE, A));
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 2장으로 생성해야 합니다.");
    }

    @Test
    @DisplayName("중복된 카드를 더할 경우 예외가 발생해야 한다.")
    void addExceptionBySumIsLargerThanBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, NINE)));
        assertThatThrownBy(() -> cards.addCard(Card.of(SPADE, TEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드를 추가할 수 없습니다.");
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
        final Cards compareCards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK)));
        compareCards.addCard(Card.of(HEART, A));
        assertThat(cards.fightResult(compareCards)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("상대방만 블랙잭인 경우 패배를 반환한다.")
    void fightResultNotSelfBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK)));
        cards.addCard(Card.of(HEART, A));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A)));
        assertThat(cards.fightResult(compareCards)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("모두 블랙잭이 아닌 경우 숫자로 비교한다.")
    void fightResultBothNotBlackJack() {
        final Cards cards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK)));
        cards.addCard(Card.of(HEART, A));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, JACK)));
        compareCards.addCard(Card.of(SPADE, A));
        assertThat(cards.fightResult(compareCards)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트일 경우, 무승부를 반환한다.")
    void fightResultBothBust() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN)));
        cards.addCard(Card.of(SPADE, JACK));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, QUEEN)));
        compareCards.addCard(Card.of(HEART, JACK));
        assertThat(cards.fightResult(compareCards)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("본인만 버스트일 경우, 패배를 반환한다.")
    void fightResultSelfBust() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN)));
        cards.addCard(Card.of(SPADE, JACK));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, QUEEN)));
        assertThat(cards.fightResult(compareCards)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("상대만 버스트일 경우, 우승을 반환한다.")
    void fightResultNotSelfBust() {
        final Cards cards = new Cards(Arrays.asList(Card.of(HEART, KING), Card.of(HEART, QUEEN)));
        final Cards compareCards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN)));
        compareCards.addCard(Card.of(SPADE, JACK));
        assertThat(cards.fightResult(compareCards)).isEqualTo(WIN);
    }
}
