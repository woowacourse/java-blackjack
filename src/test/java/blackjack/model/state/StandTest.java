package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.model.results.Result;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StandTest {
    private Stand stand;

    @BeforeEach
    void setUp() {
        Cards standCards = new Cards(
                List.of(new Card(CardNumber.THREE, CardShape.SPADE),
                        new Card(CardNumber.TEN, CardShape.HEART))
        );
        stand = new Stand(standCards);
    }

    @DisplayName("stand 상태일 때 추가 카드를 뽑을 수 없다")
    @Test
    void draw() {
        Card card = new Card(CardNumber.THREE, CardShape.CLOVER);

        assertThatThrownBy(() -> stand.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태에서는 카드 여러장을 뽑을 수 없다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatThrownBy(() -> stand.drawCards(cardsToAdd))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태에서 카드뽑기를 멈출 수 없다")
    @Test
    void stand() {
        assertThatThrownBy(() -> stand.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태일때 상대방이 bust이면 이긴다")
    @Test
    void evaluateResultOtherBust() {
        State otherState = new Bust(new Cards());
        assertThat(stand.determineResult(otherState)).isEqualTo(Result.WIN);
    }

    @DisplayName("stand 상태일때 상대방이 블랙잭이면 진다")
    @Test
    void evaluateResultOtherBlackJack() {
        State otherState = new BlackJack(new Cards(List.of(
                new Card(CardNumber.ACE, CardShape.SPADE),
                new Card(CardNumber.TEN, CardShape.HEART)))
        );

        assertThat(stand.determineResult(otherState)).isEqualTo(Result.LOSE);
    }

    @DisplayName("stand 상태일때 상대방의 상태도 stand인 경우 점수에 따라 승패가 결정된다")
    @ParameterizedTest
    @CsvSource(value = {"TWO,WIN", "THREE,PUSH", "FOUR,LOSE"})
    void evaluateResultOtherStand(CardNumber cardNumber, Result expected) {
        Cards otherCards = new Cards(List.of(
                new Card(cardNumber, CardShape.SPADE),
                new Card(CardNumber.TEN, CardShape.HEART))
        );
        State otherState = new Stand(otherCards);

        assertThat(stand.determineResult(otherState)).isEqualTo(expected);
    }

    @DisplayName("stand 상태의 카드 점수를 계산한다")
    @Test
    void getScore() {
        assertThat(stand.getScore()).isEqualTo(13);
    }
}
