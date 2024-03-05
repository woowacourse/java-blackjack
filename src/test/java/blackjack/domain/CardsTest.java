package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("뽑은 카드를 저장한다.")
    void cardsCreateTest() {
        // given & when
        Cards cards = new Cards(List.of(Card.SPADE_NINE, Card.CLUB_FIVE));

        // then
        assertThat(cards.getValues().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        int expectedScore = 9 + 5;

        // when
        Cards cards = new Cards(List.of(Card.SPADE_NINE, Card.CLUB_FIVE));

        // then
        assertThat(cards.totalScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given


        // when
        Cards cards = new Cards(List.of(Card.SPADE_NINE, Card.CLUB_QUEEN, Card.CLUB_ACE, Card.HEART_ACE));

        // then
        assertThat(cards.totalScore()).isEqualTo(21);
    }

}
