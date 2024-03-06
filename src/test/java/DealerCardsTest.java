import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerCardsTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        dealerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(dealerCards.calculateScore()).isEqualTo(9);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.")
    @Test
    void lowerThanBustThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        dealerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(dealerCards.hasScoreUnderBustThreshold()).isTrue();
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 16 이하인지 알려준다.")
    @Test
    void lowerThanCanHitThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.SIX, CardShape.SPADE));

        assertThat(dealerCards.hasScoreUnderHitThreshold()).isTrue();
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 17 이상인지 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.SEVEN, CardShape.SPADE));

        assertThat(dealerCards.hasScoreUnderHitThreshold()).isFalse();
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 이하인 경우 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.FIVE, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.FIVE, CardShape.SPADE));
        dealerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(dealerCards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 초과인 경우 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.K, CardShape.SPADE));
        dealerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(dealerCards.calculateScore()).isEqualTo(21);
    }
}
