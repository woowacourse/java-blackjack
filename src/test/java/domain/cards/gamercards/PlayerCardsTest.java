package domain.cards.gamercards;

import static org.assertj.core.api.Assertions.assertThat;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCardsTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());
        playerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        playerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        playerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(playerCards.calculateScore()).isEqualTo(9);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.")
    @Test
    void lowerThanThresholdTest() {
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());
        playerCards.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        playerCards.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        playerCards.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(playerCards.hasScoreUnderBustThreshold()).isTrue();
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 이하인 경우 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());
        playerCards.addCard(new Card(CardNumber.FIVE, CardShape.HEART));
        playerCards.addCard(new Card(CardNumber.FIVE, CardShape.SPADE));
        playerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(playerCards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 초과인 경우 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());
        playerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        playerCards.addCard(new Card(CardNumber.K, CardShape.SPADE));
        playerCards.addCard(new Card(CardNumber.A, CardShape.SPADE));

        assertThat(playerCards.calculateScore()).isEqualTo(21);
    }
}
