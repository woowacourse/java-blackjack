import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GamerCardsTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        GamerCards gamerCards = new GamerCards(new ArrayList<>());
        gamerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        gamerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        gamerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(gamerCards.calculateScore()).isEqualTo(9);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.")
    @Test
    void lowerThanThresholdTest() {
        GamerCards gamerCards = new GamerCards(new ArrayList<>());
        gamerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        gamerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        gamerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(gamerCards.hasScoreUnderBustThreshold()).isTrue();
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 이하인 경우 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        GamerCards gamerCards = new GamerCards(new ArrayList<>());
        gamerCards.addCard(new Card(CardNumber.FIVE, CardShape.HEART));
        gamerCards.addCard(new Card(CardNumber.FIVE, CardShape.SPADE));
        gamerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(gamerCards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 초과인 경우 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        GamerCards gamerCards = new GamerCards(new ArrayList<>());
        gamerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        gamerCards.addCard(new Card(CardNumber.K, CardShape.SPADE));
        gamerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(gamerCards.calculateScore()).isEqualTo(21);
    }
}
