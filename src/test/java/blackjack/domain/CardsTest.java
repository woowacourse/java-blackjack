package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("뽑은 카드를 저장한다.")
    void cardsCreateTest() {
        // given & when
        Cards cards = new Cards();
        cards.addCard(Card.SPADE_NINE);
        cards.addCard(Card.CLUB_ACE);

        // then
        assertThat(cards.getValues().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        int expectedScore = 9 + 5;

        // when
        Cards cards = new Cards();
        cards.addCard(Card.CLUB_FIVE);
        cards.addCard(Card.SPADE_NINE);

        // then
        assertThat(cards.totalScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Cards cards = new Cards();

        // when
        cards.addCard(Card.SPADE_NINE);
        cards.addCard(Card.CLUB_QUEEN);
        cards.addCard(Card.CLUB_ACE);
        cards.addCard(Card.HEART_ACE);

        // then
        assertThat(cards.totalScore()).isEqualTo(21);
    }

}
